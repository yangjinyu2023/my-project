package com.example.demo.algorithm.interview;

import java.util.concurrent.Semaphore;

/**
 * <a href="https://blog.csdn.net/oBoySen/article/details/107901633">有三个线程顺序打印abc十次，请用线程同步实现</a>
 *
 * @author yangjinyu
 * @time 2022/10/19 10:28
 */
public class T4 {

    public static void main(String[] args) {
        new T4().cyclicPrint();
    }

    // 因为count不涉及并发操作，不需要原子操作，也不需要volatile
    private int count;

    public void cyclicPrint() {
        Semaphore a = new Semaphore(1);
        Semaphore b = new Semaphore(0);
        Semaphore c = new Semaphore(0);
        new Thread(new Task(a, b)).start();
        new Thread(new Task(b, c)).start();
        new Thread(new Task(c, a)).start();
    }

    class Task implements Runnable {

        private final Semaphore acquire;

        private final Semaphore release;

        public Task(Semaphore acquire, Semaphore release) {
            this.acquire = acquire;
            this.release = release;
        }

        @Override
        public void run() {
            while (count < 30) {
                try {
                    acquire.acquire();
                    if (count < 30) {
                        System.out.println(Thread.currentThread() + "=>" + count);
                        count++;
                    }
                    release.release();
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}