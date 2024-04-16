package com.playground.spring.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/aop")
@RestController
public class AopController {
    @GetMapping("/log")
    public ResponseEntity log() {
        return ResponseEntity.ok("log");
    }
}
