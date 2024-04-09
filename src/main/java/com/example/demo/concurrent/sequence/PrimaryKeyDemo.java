package com.example.demo.concurrent.sequence;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PrimaryKeyDemo {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            executor.execute(() ->{
                for (int j = 0; j < 100; j++) {
                    System.out.println(PrimaryKeyGenerator.getInstance().getPrimaryKey());
                }
            });
        }
        executor.shutdown();
        Thread.sleep(10000);
    }
}