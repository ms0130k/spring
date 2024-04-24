package com.playground.spring.config.resttemplate;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class RestTemplateConfig {
    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();

        // 인터셉터 리스트 생성
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();

        // 첫 번째 인터셉터
        interceptors.add(new ClientHttpRequestInterceptor() {
            @Override
            public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
                System.out.println("First Interceptor: Pre-processing request");
                ClientHttpResponse response = execution.execute(request, body);
                System.out.println("First Interceptor: Post-processing response");
                return response;
            }
        });

        // 두 번째 인터셉터
        interceptors.add(new ClientHttpRequestInterceptor() {
            @Override
            public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
                System.out.println("Second Interceptor: Pre-processing request");
                ClientHttpResponse response = execution.execute(request, body);
                System.out.println("Second Interceptor: Post-processing response");
                return response;
            }
        });

        // 인터셉터 리스트를 RestTemplate에 설정
        restTemplate.setInterceptors(interceptors);
        return restTemplate;
    }

    @Bean
    public RestTemplate restTemplateForTimeout() {
        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();


        // 인터셉터 리스트 생성

        // 첫 번째 인터셉터
        interceptors.add(new ClientHttpRequestInterceptor() {
            @Override
            public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
                System.out.println("First Interceptor: Pre-processing request");
                ClientHttpResponse response = execution.execute(request, body);
                System.out.println("First Interceptor: Post-processing response");
                return response;
            }
        });

        // 두 번째 인터셉터
        interceptors.add(new ClientHttpRequestInterceptor() {
            @Override
            public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
                System.out.println("Second Interceptor: Pre-processing request");
                ClientHttpResponse response = execution.execute(request, body);
                System.out.println("Second Interceptor: Post-processing response");
                return response;
            }
        });

        RestTemplateBuilder builder = new RestTemplateBuilder();

        return builder.interceptors(interceptors)
                .build();
    }
}
