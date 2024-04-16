package com.playground.spring.config.resttemplate;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class LoggingRequestResponseInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        // 요청 바디 로깅
        logRequest(request, body);

        // 요청 실행
        ClientHttpResponse response = execution.execute(request, body);

        // 응답 로깅을 위한 래핑
        return wrapAndLogResponse(response);
    }

    private void logRequest(HttpRequest request, byte[] body) {
        System.out.println("Request URI: " + request.getURI());
        System.out.println("Request Method: " + request.getMethod());
        System.out.println("Request Headers: " + request.getHeaders());
        System.out.println("Request Body: " + new String(body, StandardCharsets.UTF_8));
    }

    private ClientHttpResponse wrapAndLogResponse(ClientHttpResponse response) throws IOException {
        // 응답 바디를 바이트 배열로 복사
        byte[] body = StreamUtils.copyToByteArray(response.getBody());
        // 응답 바디 로깅
        System.out.println("Response Body: " + new String(body, StandardCharsets.UTF_8));

        // 새로운 InputStream 생성하여 래핑된 응답 반환
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body);
        return new ClientHttpResponseWrapper(response, byteArrayInputStream);
    }

    private static class ClientHttpResponseWrapper implements ClientHttpResponse {

        private final ClientHttpResponse originalResponse;
        private final InputStream body;

        public ClientHttpResponseWrapper(ClientHttpResponse originalResponse, ByteArrayInputStream body) {
            this.originalResponse = originalResponse;
            this.body = body;
        }

        @Override
        public HttpStatusCode getStatusCode() throws IOException {
            return originalResponse.getStatusCode();
        }

        @Override
        public String getStatusText() throws IOException {
            return originalResponse.getStatusText();
        }

        @Override
        public HttpHeaders getHeaders() {
            return originalResponse.getHeaders();
        }

        @Override
        public InputStream getBody() {
            return this.body;
        }

        @Override
        public void close() {
            originalResponse.close();
        }
    }
}

