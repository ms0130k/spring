package com.playground.spring.config;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HttpHeadersTest {
    @Test
    public void add_set_차이() {
        HttpHeaders headers = new HttpHeaders();

        headers.add("key", "value1");
        headers.add("key", "value2");

        List<String> strings = headers.get("key");
        assertEquals(2, strings.size());
        assertEquals("value2", strings.get(1));

        headers.set("key", "value3");
        assertEquals(1, headers.get("key").size());
        assertEquals("value3", headers.get("key").get(0));
    }
}
