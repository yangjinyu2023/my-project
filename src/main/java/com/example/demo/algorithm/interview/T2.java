package com.example.demo.algorithm.interview;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <a href="https://blog.csdn.net/oBoySen/article/details/107901633">有三个线程顺序打印abc十次，请用线程同步实现</a>
 *
 * @author yangjinyu
 * @time 2022/10/19 10:28
 */
public class T2 {

    // T2、T3、T4分别用synchronized、reentrantLock+condition、semaphore实现
    public static void main(String[] args) {
        // new T2().cyclicPrint();
        new T2().cyclicPrint();
    }

    public void cyclicPrint() {
        ExecutorService pool = Executors.newFixedThreadPool(3);
        pool.execute(new Task(0));
        pool.execute(new Task(1));
        pool.execute(new Task(2));
        pool.shutdown();
    }

    // 因为count不涉及并发操作，不需要原子操作，也不需要volatile
    private int count;

    private final Object lock = new Object();

    class Task implements Runnable {
        private final int remainder;

        public Task(int remainder) {
            this.remainder = remainder;
        }

        @Override
        public void run() {
            while (count < 30) {
                synchronized (lock) {
                    if (count < 30 && count % 3 == remainder) {
                        System.out.println(Thread.currentThread() + "=>" + count);
                        count++;
                        lock.notifyAll();
                    }
                    else {
                        try {
                            lock.wait();
                        }
                        catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        }
    };

    // public void cyclicPrint() {
    // Thread t1 = new Thread(() -> {
    // while (count < 30) {
    // synchronized (lock) {
    // if (match("A")) {
    // System.out.println("A");
    // count++;
    // }
    // else {
    // try {
    // lock.wait();
    // }
    // catch (InterruptedException e) {
    // e.printStackTrace();
    // }
    // }
    // lock.notifyAll();
    // }
    // }
    // });
    // Thread t2 = new Thread(() -> {
    // while (count < 30) {
    // synchronized (lock) {
    // if (match("B")) {
    // System.out.println("B");
    // count++;
    // }
    // else {
    // try {
    // lock.wait();
    // }
    // catch (InterruptedException e) {
    // e.printStackTrace();
    // }
    // }
    // lock.notifyAll();
    // }
    // }
    // });
    // Thread t3 = new Thread(() -> {
    // while (count < 30) {
    // synchronized (lock) {
    // if (match("C")) {
    // System.out.println("C");
    // count++;
    // }
    // else {
    // try {
    // lock.wait();
    // }
    // catch (InterruptedException e) {
    // e.printStackTrace();
    // }
    // }
    // lock.notifyAll();
    // }
    // }
    // });
    //
    // t1.start();
    // t2.start();
    // t3.start();
    // }
    //
    // private boolean match(String s) {
    // if (count % 3 == 0 && s.equals("A")) {
    // return true;
    // }
    // if (count % 3 == 1 && s.equals("B")) {
    // return true;
    // }
    // if (count % 3 == 2 && s.equals("C")) {
    // return true;
    // }
    // return false;
    // }
}