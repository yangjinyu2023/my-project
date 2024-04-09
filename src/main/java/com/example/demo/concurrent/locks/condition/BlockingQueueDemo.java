package com.example.demo.concurrent.locks.condition;

public class BlockingQueueDemo {
    public static void main(String[] args) {
        MyBlockingQueue<Integer> queue = new MyBlockingQueue<>(5);
        new Thread(() -> {
            try {
                for (int i = 1; i < 10; i++) {
                    queue.put(i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            try {
                for (int i = 1; i < 10; i++) {
                    Integer a = queue.take();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}