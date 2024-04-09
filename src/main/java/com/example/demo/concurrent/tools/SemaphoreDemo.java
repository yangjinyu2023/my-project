package com.example.demo.concurrent.tools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreDemo {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        // 只能5个线程同时访问
        final Semaphore semaphore = new Semaphore(1);
        for (int i = 0; i < 20; i++) {
            final int NO = i + 1;
            Runnable runnable = () -> {
                try {
                    // 获取许可
                    semaphore.acquire();
                    System.out.println("访问" + NO);
                    Thread.sleep((long) (Math.random() * 10000));
                    // 访问完成，释放
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
            executorService.execute(runnable);
        }
        executorService.shutdown();
    }
}