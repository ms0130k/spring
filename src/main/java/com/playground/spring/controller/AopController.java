package com.playground.spring.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import static java.nio.charset.StandardCharsets.UTF_8;

@Slf4j
@RequestMapping("/aop")
@RestController
public class AopController {
    @PostMapping("/onlyrequest")
    public ResponseEntity onlyrequest(HttpServletRequest request) throws IOException {
        byte[] bytes = request.getInputStream().readAllBytes();
        String body = new String(bytes, UTF_8);

        if (StringUtils.isEmpty(body)) {
            throw new RuntimeException("body is empty");
        }
        return ResponseEntity.ok("onlyrequest");
    }

    @PostMapping("/stringbody")
    public ResponseEntity stringbody(HttpServletRequest request, @RequestBody String body) throws IOException {
        if (StringUtils.isEmpty(body)) {
            throw new RuntimeException("body is empty");
        }
        return ResponseEntity.ok("stringbody");
    }

    @PostMapping("/mapbody")
    public ResponseEntity mapbody(HttpServletRequest request, @RequestBody String body) throws IOException {
        if (StringUtils.isEmpty(body)) {
            throw new RuntimeException("body is empty");
        }
        return ResponseEntity.ok("mapbody");
    }
}
