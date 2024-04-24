package com.playground.spring.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@Slf4j
@RequestMapping("/api")
@RestController
public class ApiController {
    @PostMapping("/onlyrequest")
    public String onlyrequest(HttpServletRequest request) {
        try {
            log.info("controller body 추출: {}", new String(request.getInputStream().readAllBytes(), StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "onlyrequest";
    }

    @PostMapping("/stringbody")
    public String onlyrequest(HttpServletRequest request, @RequestBody String body) {
        try {
            log.info("controller body 추출: {}", new String(request.getInputStream().readAllBytes(), StandardCharsets.UTF_8));
            log.info("string body: {}", body);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "onlyrequest";
    }

    @PostMapping("/mapbody")
    public String onlyrequest(HttpServletRequest request, @RequestBody Map body) {
        log.info("map body: {}", body.toString());
//        try {
//            log.info("controller body 추출: {}", new String(request.getInputStream().readAllBytes(), StandardCharsets.UTF_8));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        return "onlyrequest";
    }

    @GetMapping("/throw")
    public String throwException(@RequestParam(required = false) String throwParam) {
        throw new RuntimeException("throwParam in controller");
    }
}
