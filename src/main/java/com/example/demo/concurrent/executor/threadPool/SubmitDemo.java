package com.example.demo.concurrent.executor.threadPool;

import com.example.demo.domain.Data;

import java.util.concurrent.*;

public class SubmitDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<String> callable = () -> {
            System.out.println("This is ThreadPoolExecutor#submit(Callable<T> task) method.");
            Thread.sleep(2000);
            return "result";
        };

        Data data = new Data();
        Runnable task = () -> {
            System.out.println("This is ThreadPoolExecutor#submit(Runnable task, T result) method.");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            data.setName("kevin");
        };

        // 1.传入callable
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> future1 = executor.submit(callable);
        System.out.println(future1.get());

        // 2.传入runnable和result
        Future<Data> future2 = executor.submit(task, data);
        System.out.println(future2.get().getName());

        // 3.只传入runnable，会等待线程结束，但是获取结果为null
        Future<?> future3 = executor.submit(task);
        System.out.println(future3.get());


        executor.shutdown();
    }
}