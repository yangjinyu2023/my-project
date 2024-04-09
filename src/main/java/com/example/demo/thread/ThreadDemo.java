package com.example.demo.thread;

import org.junit.Test;

import java.util.concurrent.locks.LockSupport;

public class ThreadDemo {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("主线程：" + Thread.currentThread().getName());
        Thread thread = new Thread(()->{
            LockSupport.park();
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException("hhhhhh");
            }
        });
        thread.start();
        Thread.sleep(100);
        System.out.println(thread.getState());
        LockSupport.unpark(thread);
        thread.interrupt();
        System.out.println();
    }
    @Test
    public void testJoin() throws InterruptedException {
        //如果一个线程已经跑完了，在使用它的join方法会发生什么？
        Thread thread = new Thread(()-> System.out.println("111"));
        thread.start();
        Thread.sleep(100);
        thread.join();
        System.out.println("222");
    }
}