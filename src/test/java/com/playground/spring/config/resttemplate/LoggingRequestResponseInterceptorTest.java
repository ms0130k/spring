package com.playground.spring.config.resttemplate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.client.response.MockRestResponseCreators;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.client.ExpectedCount.once;

class LoggingRequestResponseInterceptorTest {

    private RestTemplate restTemplate;
    private MockRestServiceServer mockServer;

    @BeforeEach
    void setUp() {
        restTemplate = new RestTemplate();
        restTemplate.setInterceptors(Collections.singletonList(new LoggingRequestResponseInterceptor()));
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    void testInterceptorWithMockServer() {
        String expectedUrl = "http://example.com/test";
        String requestBody = "Hello, world!";
        String responseBody = "OK";

        mockServer.expect(once(), MockRestRequestMatchers.requestTo(expectedUrl))
                .andExpect(MockRestRequestMatchers.method(HttpMethod.POST))
                .andExpect(MockRestRequestMatchers.content().string(requestBody))
                .andRespond(MockRestResponseCreators.withSuccess(responseBody, MediaType.TEXT_PLAIN));

        // 실행
        String s = restTemplate.postForObject(expectedUrl, requestBody, String.class);

        assertEquals("OK", s);

        // 모의 서버가 설정된 요청/응답을 올바르게 처리했는지 확인
        mockServer.verify();
    }
}
