package com.example.demo.concurrent.executor.future;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

@Component
public class CompletableFutureDemo {

    @Bean("myThreadPoolExecutor")
    public ThreadPoolExecutor getThreadPoolExecutor() {
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("MY-THREAD-POOL").build();
        return new ThreadPoolExecutor(5, 60, 10, TimeUnit.SECONDS, new LinkedBlockingQueue <>(100), threadFactory,
                new ThreadPoolExecutor.CallerRunsPolicy());
    }

    // https://www.jianshu.com/p/48b5b7c319ca
    public static void main(String[] args) {
        List < Integer > params = Arrays.asList(1, 2, 3, 4, 5, 6);
        ExecutorService executor = Executors.newFixedThreadPool(5);

        // 6个任务，异步执行
        List < CompletableFuture < Double > > futureList = params.stream()
                .map(param -> CompletableFuture.supplyAsync(() -> param + Math.random()*10, executor))
                .collect(Collectors.toList());
        // 等待任务全执行完，返回结果
        List < Double > resultList = futureList.stream().map(CompletableFuture::join).collect(Collectors.toList());
        System.out.println(resultList);

        // allOf
        CompletableFuture.allOf(params.stream().map(param -> CompletableFuture.runAsync(() -> {
            String name = Thread.currentThread().getName();
            System.out.println(name + " start task" + param);
        }, executor)).toArray(CompletableFuture[]::new)).whenComplete((r, t) -> {
            executor.shutdown();
            System.out.println(r);
        });

        CompletableFuture < Integer > future = CompletableFuture.supplyAsync(() -> {
            int a = 100;
            int b = a + 10;
            return a * b;
        });
        System.out.println(future.thenApply(a -> a + 10).join());
        System.out.println(111);
    }
}