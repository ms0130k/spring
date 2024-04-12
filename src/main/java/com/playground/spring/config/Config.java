package com.playground.spring.config;

import com.playground.spring.etc.Car;
import com.playground.spring.etc.Santafe;
import com.playground.spring.etc.Sonata;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public Car santafe() {
        return new Santafe();
    }
    @Bean
    public Car sonata() {
        return new Sonata();
    }
}
