package com.playground.spring.controller;

import com.playground.spring.etc.Car;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class PalyController {
    private final Car santafe;
    private final Environment env;
    public PalyController(Car sonata, Environment env) {
        this.santafe = sonata;
        this.env = env;
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

    @GetMapping("/profile")
    public String profile() {
        return env.getActiveProfiles().length > 0 ? String.join(", ", env.getActiveProfiles()) : "No Active Profile";
    }

}
