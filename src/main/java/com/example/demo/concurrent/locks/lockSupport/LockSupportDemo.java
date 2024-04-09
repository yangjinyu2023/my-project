package com.example.demo.concurrent.locks.lockSupport;

import java.util.concurrent.locks.LockSupport;

public class LockSupportDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(() -> {
            System.out.println("threadA start");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("threadA wait");
            LockSupport.park();
            System.out.println("threadA end");
        },"ThreadA");

        threadA.start();
        System.out.println("main doing Something");
        Thread.sleep(2000);
        System.out.println("main finish Something");
        LockSupport.unpark(threadA);
    }
}