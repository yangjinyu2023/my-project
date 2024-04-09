package com.example.demo.concurrent.executor.future;

import lombok.SneakyThrows;
import org.springframework.util.concurrent.ListenableFutureTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureTaskCallbackDemo {
    @SneakyThrows
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        try {
            List<Future<Boolean>> futures = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                int finalI = i;
                ListenableFutureTask<Boolean> future = new ListenableFutureTask<>(() -> {
                    try {
                        System.out.println(finalI + " running");
                        Thread.sleep(300);
                        System.out.println(finalI + " done");
                        return true;
                    } finally {
                        System.out.println("---------");
                    }
                });
                future.addCallback(result -> System.out.println("success"), ex -> System.out.println("fail"));
                executor.execute(future);
            }
            for (Future<Boolean> future : futures) {
                future.get();
            }
        } finally {
            executor.shutdown();
        }
    }
}