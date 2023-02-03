package com.example.clubsite.security.filter;

import com.example.clubsite.security.util.JWTUtil;
import com.example.clubsite.wrapper.RereadableRequestWrapper;
import com.nimbusds.jose.shaded.json.JSONObject;
import lombok.extern.log4j.Log4j2;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


//인증 헤더의 JWT 토큰을 확인해서 권한 체크
@Log4j2
public class ReReadableRequestFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        log.info("Reredable!");
        RereadableRequestWrapper rereadableRequestWrapper = new RereadableRequestWrapper((HttpServletRequest)request);


        filterChain.doFilter(rereadableRequestWrapper,response); //다음 필터로 넘어가는 역할
    }



}
