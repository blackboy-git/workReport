package com.blackboy.interceptor;

import com.blackboy.controller.util.Result;
import com.blackboy.util.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.View;

import java.io.IOException;

@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtils jwtUtils;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        String token = request.getHeader("Authorization");
        String errorMsg = jwtUtils.validateToken(token);
        if (errorMsg == null) {
            return true;
        }
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(new Result(false, errorMsg).toJson());
        return false;
    }
}
