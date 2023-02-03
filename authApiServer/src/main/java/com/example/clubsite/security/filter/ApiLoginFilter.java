package com.example.clubsite.security.filter;

import com.example.clubsite.security.dto.ClubAuthMemberDTO;
import com.example.clubsite.security.handler.ApiLoginFailHandler;
import com.example.clubsite.security.util.JWTUtil;
import com.example.clubsite.wrapper.RereadableRequestWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nimbusds.jose.shaded.json.JSONObject;
import lombok.extern.log4j.Log4j2;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;


//로그인 정보를 받으면, 정보를 수신하고, JWT 토큰 발행
@Log4j2
public class ApiLoginFilter extends AbstractAuthenticationProcessingFilter {

    private JWTUtil jwtUtil;

    //private HttpServletRequest req;

    public ApiLoginFilter(String defaultFilterProcessesUrl,JWTUtil jwtUtil) {

        super(defaultFilterProcessesUrl);
        this.jwtUtil=jwtUtil; //생성자 주입을 통해 JWT를 주입받자.
    }
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {
        log.info("--------------ApiLoginFilter-----------------------");
        JSONObject jsonObject=null;
        //RereadableRequestWrapper rereadableRequestWrapper = new RereadableRequestWrapper((HttpServletRequest)request);
        try {
            jsonObject=reqToJson(request); //HttpServletRequest to JSONObject ( get ID, Password information )

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        //jsonObject.get("email")
        String id=(String) jsonObject.get("id");//request.getParameter("email");
        String pw=(String)jsonObject.get("pw");//request.getParameter("pw");

       // RereadableRequestWrapper rereadableRequestWrapper = new RereadableRequestWrapper((HttpServletRequest)request);


        log.info("id,pw,: "+id+" : "+pw);
        UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(id, pw);

        return getAuthenticationManager().authenticate(authToken);

    }

    /*
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        log.info("login fail local Handler--------------------------");
        log.info(failed.getMessage());

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        // json 으로 에러 리턴
        response.setContentType("application/json;charset=utf-8");
        JSONObject json = new JSONObject();
        String message = failed.getMessage();
        json.put("code", "401");
        json.put("message", message);

        PrintWriter out = response.getWriter();
        out.print(json);
        //super.unsuccessfulAuthentication(request, response, failed);
    }

     */


    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException, ServletException {
        //authResult는 성공한 사용자의 인증 정보를 가지고 있는 Authentication 객체이다.
        log.info("ApiLoginFilter--------------------");
        log.info("successfulAuthentication: "+authResult);
        log.info(authResult.getPrincipal());
        String email= ( (ClubAuthMemberDTO) authResult.getPrincipal() ).getUsername();//인증정보에서 유저이름(이메일) 추출
        String token=null;
        try{
            token= jwtUtil.generateToken(email); //인증 성공 시, JWT 토큰을 발행해준다.
            response.setContentType("application/json;charset=utf-8");
            JSONObject json = new JSONObject();
            String message = "";
            json.put("session",token);
            ObjectMapper mapper = new ObjectMapper();
            String jsonStr = mapper.writeValueAsString(json);
           response.getWriter().write(jsonStr);

           // response.getOutputStream().write(json);
            //response.getOutputStream().write(token.getBytes());
           // log.info(:tokentoken);

        }catch (Exception e){
            e.printStackTrace();

        }
        //super.successfulAuthentication(request, response, chain, authResult);
    }



    /*
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException, ServletException {
        //authResult는 성공한 사용자의 인증 정보를 가지고 있는 Authentication 객체이다.
        log.info("ApiLoginFilter--------------------");
        log.info("successfulAuthentication: "+authResult);
        log.info(authResult.getPrincipal());
        String body = null;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;

        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            }
        } catch (IOException ex) {
            throw ex;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    throw ex;
                }
            }
        }

        body = stringBuilder.toString();
       // return body;

    }

     */

    public JSONObject reqToJson(HttpServletRequest request) throws IOException, ParseException {

        String body = null;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;

        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            }
        } catch (IOException ex) {
            throw ex;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    throw ex;
                }
            }
        }

        body = stringBuilder.toString();
        log.info("body: "+body);
        //JSONParser parser = new JSONParser();  deprecated
        //JSONObject jsonObject = (JSONObject) parser.parse(body);
        JSONObject jsonObject=new Gson().fromJson(body, JSONObject.class);

        return jsonObject;


    }



}
