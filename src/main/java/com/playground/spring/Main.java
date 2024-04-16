package com.playground.spring;

import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {

        try {
//            CompletableFuture<String> completableFuture = new Main().createCompletableFuture(5);
//            String s = completableFuture.get(5, TimeUnit.SECONDS);
//            System.out.println(s);
//
//            CompletableFuture<String> completableFuture2 = new Main().createCompletableFuture(0);
//            String s2 = completableFuture2.get();
//            System.out.println(s2);

            CompletableFuture<String> completableFuture3 = new Main().createCompletableFuture(7);
            String s3 = completableFuture3.get(5, TimeUnit.SECONDS);
            System.out.println(s3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            System.out.println("TimeoutException: " + e.getMessage());
        }
    }

    private static CompletableFuture<String> createCompletableFuture(int secondsToSleep) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                if (secondsToSleep > 0) {
                    Thread.sleep(secondsToSleep * 1000);  // Sleep in the asynchronous task
                }
                return "Hello, Sleep!";
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // set the interrupt flag
                return "Interrupted!";
            }
        });
    }
}
