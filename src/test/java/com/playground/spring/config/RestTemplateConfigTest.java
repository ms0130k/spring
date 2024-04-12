package com.playground.spring.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.client.response.MockRestResponseCreators;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class RestTemplateConfigTest {
    @Autowired
    private RestTemplate restTemplate;
    private MockRestServiceServer mockServer;

    @BeforeEach
    public void init() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
    }

    @Test
    public void testRestTemplateInterceptors() {
        // 설정된 URL에 대한 응답을 모의
        mockServer.expect(MockRestRequestMatchers.requestTo("http://example.com"))
                .andExpect(MockRestRequestMatchers.method(HttpMethod.GET))
                .andRespond(MockRestResponseCreators.withSuccess("response body", MediaType.TEXT_PLAIN));

        // RestTemplate 호출
        String result = restTemplate.getForObject("http://example.com", String.class);

        // 결과 검증
        assertEquals("response body", result);

        // 모든 예상이 만족되었는지 확인
        mockServer.verify();
    }
}