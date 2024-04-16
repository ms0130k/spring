package com.playground.spring.controller;

import com.playground.spring.config.aop.AroundAop;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AopController.class)
@Import(AroundAop.class)
class AopControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void log() throws Exception {
        mockMvc.perform(get("/aop/log"))
                .andExpect(status().isOk())
                .andExpect(content().string("log"));
    }
}