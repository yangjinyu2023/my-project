package com.example.demo.concurrent.executor.future;

import java.util.concurrent.*;

public class FutureTaskDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        // FutureTask
        FutureTask<String> task = new FutureTask<>(new MyCallable());
        executor.execute(task);
        task.get();
        // Future
        Future<String> future = executor.submit(new MyCallable());
        future.get();
        // FutureTask 实现 Future，同时实现了 Runnable
        FutureTask<Integer> futureTask = new FutureTask<>(() -> 1 + 1);
        executor.execute(futureTask);
        futureTask.get();
        executor.shutdown();
    }
}