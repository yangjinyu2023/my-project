package com.example.demo.thread;

/**
 * @description:
 * @author: yangjinyu
 * @time: 2020/1/28 20:28
 */
public class YieldDemo {
    public static void main(String[] args) {
        Runnable yieldTask = () -> {
            for (int i = 0; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + i);
                if (i == 5) {
                    Thread.yield();
                }
            }
        };

        Thread a = new Thread(yieldTask, "A");
        Thread b = new Thread(yieldTask, "B");
        a.start();
        b.start();

    }
}