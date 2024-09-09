package com.cx_project.kiiun.global.auth.filter;

import com.cx_project.kiiun.global.exception.CustomExceptionEntity;
import com.cx_project.kiiun.global.exception.ErrorCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class UnAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper mapper;

    @Autowired
    public UnAuthenticationEntryPoint(ObjectMapper mapper){
        this.mapper = mapper;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(ErrorCode.UNAUTHENTICATED_MEMBER.getStatus().value());
        CustomExceptionEntity error = new CustomExceptionEntity(ErrorCode.UNAUTHENTICATED_MEMBER.getCode(),ErrorCode.UNAUTHENTICATED_MEMBER.getMessage());
        response.getWriter().write(mapper.writeValueAsString(error));
    }
}