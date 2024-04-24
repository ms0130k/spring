package com.playground.spring.config.filter;

import com.playground.spring.config.CachedBodyHttpServletRequest;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
public class ThrowFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String aThrow = request.getParameter("throwParam");
        if (aThrow != null) {
            throw new RuntimeException("throwParam");
        }
        chain.doFilter(request, response);
    }
}