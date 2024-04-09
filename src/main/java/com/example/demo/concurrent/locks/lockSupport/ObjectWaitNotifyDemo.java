package com.example.demo.concurrent.locks.lockSupport;

/**
 * @description:
 * @author: yangjinyu
 * @time: 2020/1/31 17:00
 */
public class ObjectWaitNotifyDemo {
    public static void main(String[] args) throws InterruptedException {
        final Object obj = new Object();
        Thread threadA = new Thread(() -> {
            System.out.println("threadA start");
            try {
                // 使用wait/notify/notifyAll必须在同步环境中
                synchronized (obj) {
                    System.out.println("threadA wait");
                    obj.wait();
                    System.out.println("threadA end");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "ThreadA");

        threadA.start();
        // 睡眠一秒钟，保证线程A执行wait方法，否则可能一直wait
        System.out.println("main doing Something");
        Thread.sleep(1000);
        System.out.println("main finish Something");
        synchronized (obj) {
            obj.notify();
        }
    }
}