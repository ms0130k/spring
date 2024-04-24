package com.playground.spring.config.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class AuthenticationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        log.info("Authentication filter start");
        HttpServletRequest req = (HttpServletRequest) request;
        log.info("Request Logged: {} {}", req.getMethod(), req.getRequestURI());
        chain.doFilter(request, response);
        log.info("Authentication filter end");
    }
}