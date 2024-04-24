package com.playground.spring;

import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        CloneTest cloneTest = new CloneTest();
        cloneTest.name = "name";
        cloneTest.age = 10;
        CloneTest cloneTest1 = cloneTest.clone();
        cloneTest1.name = "name1";
        cloneTest1.age = 20;
        System.out.println(cloneTest.name);
        System.out.println(cloneTest.age);
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

class CloneTest implements Cloneable {
    public String name;
    public long age;

    @Override
    public CloneTest clone() {
        try {
            return (CloneTest) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}