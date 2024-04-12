package com.playground.spring.controller;

import com.playground.spring.etc.Car;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class PalyController {
    private final Car santafe;
    public PalyController(Car sonata) {
        this.santafe = sonata;
    }

    @GetMapping("/hello")
    public String hello() {
        santafe.run();
        return "Hello, World!";
    }

    @GetMapping("/cust")
    public String error(HttpServletResponse response) throws IOException {
        response.sendError(502, "Error!!!!!");
        return "Error!!";
    }
}
