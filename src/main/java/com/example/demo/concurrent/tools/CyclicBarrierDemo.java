package com.example.demo.concurrent.tools;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierDemo {
    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
       // new CyclicBarrierDemo().go();
        new CyclicBarrierDemo().gogo();
    }

    private void go() {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, new Producer("蛋糕"));
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.execute(new Producer(cyclicBarrier, "面粉"));
        executorService.execute(new Producer(cyclicBarrier, "油"));
        executorService.execute(new Producer(cyclicBarrier, "鸡蛋"));
        executorService.shutdown();
    }

    private void gogo() throws BrokenBarrierException, InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(4);
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.execute(new Producer(cyclicBarrier, "面粉"));
        executorService.execute(new Producer(cyclicBarrier, "油"));
        executorService.execute(new Producer(cyclicBarrier, "鸡蛋"));
        cyclicBarrier.await();
        System.out.println("原料都准备完毕，开始做蛋糕");
        executorService.shutdown();
    }

    private class Producer implements Runnable {
        private CyclicBarrier cyclicBarrier;
        private String taskName;

        Producer(String taskName) {
            this.taskName = taskName;
        }

        Producer(CyclicBarrier cyclicBarrier, String taskName) {
            this.cyclicBarrier = cyclicBarrier;
            this.taskName = taskName;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " 生产" + taskName);
            try {
                Thread.sleep((long) (Math.random() * 10000));
                System.out.println(Thread.currentThread().getName() + " " + taskName + "生产完成");
                if (cyclicBarrier != null)
                    cyclicBarrier.await();
                System.out.println(Thread.currentThread().getName() + "结束");
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}