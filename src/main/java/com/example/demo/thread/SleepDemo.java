package com.example.demo.thread;
// sleep会导致线程处理timed waiting
// 会让出cpu，但不会释放占用的锁资源
public class SleepDemo {

    public static void main(String[] args) {
        Object lock = new Object();
        Thread t1 = new Thread(()->{
            synchronized (lock) {
                try {
                    System.out.println("1");
                    Thread.sleep(5000);
                    System.out.println("2");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Thread t2 = new Thread(()->{
            synchronized (lock) {
                System.out.println(3);
            }
        });
        t1.start();
        t2.start();
    }
}