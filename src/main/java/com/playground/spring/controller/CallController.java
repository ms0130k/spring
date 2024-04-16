package com.playground.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import java.util.function.Supplier;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.springframework.http.HttpMethod.GET;

@RestController
public class CallController {
    @Autowired
    RestTemplate restTemplateForTimeout;
    @GetMapping("/call/{seconds}")
    public ResponseEntity call(@PathVariable int seconds) {
        CompletableFuture<ResponseEntity<String>> future = callAsync(this::callApi);
        ResponseEntity<String> response = null;
        try {
            response = future.get(seconds, SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            response = ResponseEntity.accepted().body("Timeout");
        }
        return response;
    }

    private ResponseEntity callApi() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        ResponseEntity<String> response = restTemplateForTimeout.exchange("http://localhost:9090/hello", GET, null, String.class);
        System.out.println("response.getBody(): " + response.getBody());
        return response;
    }

    private <T> CompletableFuture<T> callAsync(Supplier<T> stringSupplier) {
        return CompletableFuture.supplyAsync(stringSupplier);
    }
}
