package com.example.demo.concurrent.executor.threadPool;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadPoolDemo {
    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int CAPACITY = (1 << COUNT_BITS) - 1;
    private static final int RUNNING = -1 << COUNT_BITS;
    private static final int SHUTDOWN = 0 << COUNT_BITS;
    private static final int STOP = 1 << COUNT_BITS;
    private static final int TIDYING = 2 << COUNT_BITS;
    private static final int TERMINATED = 3 << COUNT_BITS;

    private static int ctlOf(int rs, int wc) {
        return rs | wc;
    }

    // Executors工具类用来创建各种线程池
    public static void main(String[] args) throws InterruptedException {
        // 创建指定工作线程数量的线程池
        ExecutorService service1 = Executors.newFixedThreadPool(5);
        // invokeAll会按照任务集合中的顺序将所有的Future添加到返回的集合中，该方法是一个阻塞的方法。
        // 只有当所有的任务都执行完毕时，或者调用线程被中断，又或者超出指定时限时，invokeAll方法才会返回。
        // 当invokeAll返回之后每个任务要么返回，要么取消，此时客户端可以调用get/isCancelled来判断具体是什么情况。
        List<Future<String>> futures = service1.invokeAll(Arrays.asList(() -> "1",()->"2",()->"3"));

        // 创建大量短时间工作任务的线程池
        // 试图缓存线程并重用，当无缓存线程可用时，就会创建新的工作线程
        // 如果线程闲置的时间超过阈值，则会被终止并移出缓存
//        ExecutorService service2 = Executors.newCachedThreadPool();

        // 创建唯一的工作线程来执行任务，如果线程异常结束，会有另一个线程取代它
//        ExecutorService service3 = Executors.newSingleThreadExecutor();

        // 定时或周期性的工作调度
//        ExecutorService service4 = Executors.newSingleThreadScheduledExecutor();// 单一线程
//        ExecutorService service5 = Executors.newScheduledThreadPool(5);// 多个线程

        // 内部构建ForkJoinPool， 利用working-stealing算法，并行处理任务
//        ExecutorService service6 = Executors.newWorkStealingPool();
//        System.out.println(ctlOf(RUNNING, 0));


    }
}