package com.example.clubsite.security.handler;

import com.nimbusds.jose.shaded.json.JSONObject;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@Log4j2
public class ApiLoginFailHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {

        log.info("login fail Handler--------------------------");
        log.info(exception.getMessage());

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        String message = exception.getMessage();
        if (exception instanceof UsernameNotFoundException) {
            message = "user-not-found";
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } else if (exception instanceof BadCredentialsException) {
            message = "wrong-password";
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        } else {


            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }



        //HttpServletResponse.SC_NOT_FOUND   404 error

        // json 으로 에러 리턴
        response.setContentType("application/json;charset=utf-8");
        JSONObject json = new JSONObject();

        json.put("code", message);
        //json.put("message", message);

        PrintWriter out = response.getWriter();
        out.print(json);
    }
}
