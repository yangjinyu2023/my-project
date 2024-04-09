package com.example.demo.thread;

import com.example.demo.concurrent.executor.future.MyCallable;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @description:
 * @author: yangjinyu
 * @time: 2020/1/28 12:14
 */
public class ThreadPoolDemo {
    public static void main(String[] args) {
//        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,2,0, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
//        Future<String> future = threadPoolExecutor.submit(new MyCallable());
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> future = executorService.submit(new MyCallable());
        if (!future.isDone()) {
            System.out.println("task is running");
        }
        try {
            System.out.println(future.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
//            threadPoolExecutor.shutdown();
            executorService.shutdown();
        }
    }
}