package com.playground.spring.config.aop;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@Aspect
@Component
public class AroundAop {
    private final HttpServletRequest request;

    public AroundAop(HttpServletRequest request) {
        this.request = request;
    }

    @Pointcut("execution(public * com.playground.spring.controller.Aop*.*(..))")
    public void pointcut() {
    }

    @Around("pointcut()")
    public ResponseEntity aroundProcess(ProceedingJoinPoint joinPoint) {
        ResponseEntity result;
        try {
            String body = readBody(request);
            if (StringUtils.isEmpty(body)) {
                throw new RuntimeException("body is empty");
            }

            result = (ResponseEntity) joinPoint.proceed();
            log.info("response body: {}", result.getBody());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            result = ResponseEntity.internalServerError().build();
        }
        return result;
    }

    private String readBody(HttpServletRequest request) throws IOException {
        byte[] bytes = request.getInputStream().readAllBytes();
        return new String(bytes, StandardCharsets.UTF_8);
    }
}
