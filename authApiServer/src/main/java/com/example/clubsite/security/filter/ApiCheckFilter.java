package com.example.clubsite.security.filter;

import com.example.clubsite.security.util.JWTUtil;
import com.google.gson.Gson;
import com.nimbusds.jose.shaded.json.JSONObject;
import lombok.extern.log4j.Log4j2;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


//인증 헤더의 JWT 토큰을 확인해서 권한 체크
@Log4j2
public class ApiCheckFilter extends OncePerRequestFilter {
    private AntPathMatcher antPathMatcher; //notes/**/* 같은 타입 매칭을 위함. 지금은 필요 x
    private  String pattern;

    private String pattern2;

    private List<String> patternList;




    private JWTUtil jwtUtil;


    public ApiCheckFilter(List<String> patternList,JWTUtil jwtUtil){
        this.antPathMatcher=new AntPathMatcher();
       // this.pattern=pattern;
        this.patternList=new ArrayList<>();
        this.patternList=patternList; //newArrayList() 해줘야되나..?
        this.jwtUtil=jwtUtil;
    }



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        log.info("Request URL: "+request.getRequestURI());
       // log.info(antPathMatcher.match(pattern,request.getRequestURI()));

       boolean exist=false;
       String requestUrl=request.getRequestURI();
       String inPathName="";
       String tmp=requestUrl;
        for (String pat : patternList) {
            if(pat.equals(requestUrl)||antPathMatcher.match(pat,requestUrl)){ //antPathMatch 아직 필요 x
                if(antPathMatcher.match("/auth/verification/*",requestUrl)){ // /auth/verificaion/id 의 경우, id를 추출한다.
                    inPathName=tmp.substring(19); //문제점!
                    if(inPathName.length()==0){
                        badRequestMessage(response);
                        return; //종료! ( response 반환 )
                    }
                }
                exist=true;
            }

        }

        if(exist){
       // if(antPathMatcher.match(pattern,request.getRequestURI())){
            log.info("APICheckFilter------------------------------------------------");
            String checkHeader=checkAuthHeader(request); //인증헤더 의 추출된 값.
            //log.info("shyswychk: "+antPathMatcher.match(requestUrl,"/auth/verification/*"));
            log.info("shyswychk2: "+requestUrl);

            if(checkHeader.length()>0){ //인증 헤더의 JWT 토큰 확인 (content 값이 "" 이면 인증 실패를 나타내는 중.

               // if(antPathMatcher.match("/data/name",request.getRequestURI())){// data/name은 이름값을 반환해야함.

                if(requestUrl.equals("/data/name")){
              //  if(antPathMatcher.match("/data/name",requestUrl)){// data/name은 이름값을 반환해야함.
                   // request.setAttribute("username",checkHeader); //인증헤더 체크하며 얻은 content(이름) 값을 controller에 전달.

                    log.info("data/name의 name: "+checkHeader); //여기까지 성공!
                    // json 리턴
                    giveValueMessage(response,checkHeader);
                    return; //종료! ( response 반환 )

                }
                else if(antPathMatcher.match("/auth/verification/*",requestUrl)){


                    log.info("verification: in-path id: "+inPathName+" Token val: "+checkHeader); //여기까지 성공!
                    if(inPathName.equals(checkHeader)) {
                        // ok 상태를 json으로 response에 담아서 리턴
                        okStatusMessage(response);
                        return; //종료! ( response 반환 )
                    }
                    else{
                        badRequestMessage(response);
                        return;
                    }

                }




                filterChain.doFilter(request,response); //다음 필터로
                return;
            }
            else{


                failCheckApiMessage(response);
                return;
            }


        }

        filterChain.doFilter(request,response); //다음 필터로 넘어가는 역할
    }

    private String checkAuthHeader(HttpServletRequest request){
        boolean checkResult =false;
        //String authHeader="Bearer "+request.getHeader("Authorization"); //인증 헤더 불러오기.
        String extractedValue=null;
        String authHeader = request.getHeader("Authorization");

        JSONObject jsonObject=new Gson().fromJson(authHeader, JSONObject.class);

        String authToken="Bearer "+jsonObject.get("session").toString();// 우선은 전부 Bearer 가정해서 넣어준다 ( 프론트가 타입 안넣는다는 전제)
        log.info("authToken "+authToken);
       // request.getHeaer()
        //JSONObject authHeader= request.getHeader("Authorization"); //인증 헤더 불러오기.
       // String authToken=authHeader.get("session").toString();
        if(StringUtils.hasText(authToken)&& authToken.startsWith("Bearer ")){

            //Header은  "인증타입 ~~~~~~~~~~" 로 구성됨.
            // 여기선 Bearer이기에, 7째부터 유효한 String
            log.info("Authorization exist: "+authToken);

            try{
                extractedValue= jwtUtil.validateAndExtract(authToken.substring(7)); //"Bearer " 제거
                log.info("decode result: "+extractedValue);
                checkResult=extractedValue.length()>0; // email이 존재하면 true.
            }catch (Exception e){
                e.printStackTrace();
            }

        }
        if(checkResult) return extractedValue;
        else return ""; //인증 실패시 cotent 값을 "" 로 리턴.
       // return checkResult;
    }




    //추후 JSON에 넣을 key,value 쌍 , HttpServletResponse.SC_BAD_REQUEST) 두개 파라미터로 한개로 통일하자.
    private void badRequestMessage(HttpServletResponse response) throws IOException {
        //log.info("/auth/verificaion/id 에서 id가 비어있다");
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        response.setContentType("application/json;charset=utf-8");
        // response.set
        JSONObject json = new JSONObject();
        json.put("code", "bad-request");
        PrintWriter out = response.getWriter();
        out.print(json);
        return; //종료! ( response 반환 )
    }

    private void okStatusMessage(HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        JSONObject json = new JSONObject();


        json.put("status", "ok");

        PrintWriter out = response.getWriter();
        out.print(json);
        return; //종료! ( response 반환 )
    }

    private void giveValueMessage(HttpServletResponse response,String checkHeader) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        JSONObject json = new JSONObject();
        String message = checkHeader;

        json.put("name", message);

        PrintWriter out = response.getWriter();
        out.print(json);
        return; //종료! ( response 반환 )
    }

    private void failCheckApiMessage(HttpServletResponse response) throws IOException {
        //response.setContentType("application/json;charset=utf-8");
        response.setContentType("application/json;charset=utf-8");
        JSONObject json = new JSONObject();
        String message = "FAIL CHECK API TOKEN";
        json.put("code", "403");
        json.put("message", message);
        PrintWriter out = response.getWriter();
        out.print(json);
        return;
    }

}


