package com.playground.spring.config.filter;

import com.playground.spring.config.CachedBodyHttpServletRequest;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
public class LoggingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        log.info("logging filter start");
        HttpServletRequest req = (HttpServletRequest) request;
        request = new CachedBodyHttpServletRequest(req);
        log.info("filter body: {}", new String(request.getInputStream().readAllBytes(), StandardCharsets.UTF_8));

        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper((HttpServletResponse) response);
        chain.doFilter(request, responseWrapper);

        log.info("logging response: {}", new String(responseWrapper.getContentAsByteArray(), StandardCharsets.UTF_8));
        responseWrapper.copyBodyToResponse();
        log.info("logging filter end");
    }
}