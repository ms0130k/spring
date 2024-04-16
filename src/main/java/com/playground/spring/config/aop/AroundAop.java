package com.playground.spring.config.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Slf4j
@Aspect
@Component
public class AroundAop {
    @Around("execution(public * com.playground.spring.controller.Aop*.*(..))")
    public ResponseEntity around(ProceedingJoinPoint joinPoint) {
        ResponseEntity result = null;
//        if (true)
//            new RuntimeException("AroundAop");
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        try {
            result = (ResponseEntity) joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        stopWatch.stop();
        log.info("Execution Time: {}", stopWatch.getTotalTimeMillis());
        return result;
    }

}
