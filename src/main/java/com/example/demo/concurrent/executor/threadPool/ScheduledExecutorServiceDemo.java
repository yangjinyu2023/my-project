package com.example.demo.concurrent.executor.threadPool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorServiceDemo {
    // 定时周期执行线程池
    public static void main(String[] args) {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        // 前一次执行结束到下一次执行开始的间隔时间（间隔执行延迟时间）
        ScheduledFuture future = executor.scheduleWithFixedDelay(() -> {
                    System.out.println(111);
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(222);
                }
                , 1, 5, TimeUnit.SECONDS);
    }
}