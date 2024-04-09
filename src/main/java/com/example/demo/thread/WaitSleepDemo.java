package com.example.demo.thread;

public class WaitSleepDemo {
    public static void main(String[] args) {
        final Object lock = new Object();
        new Thread(() -> {
            System.out.println("thread A is waiting to get locks");
            synchronized (lock) {
                System.out.println("thread A get locks");
                try {
                    System.out.println("thread A is running");
                    Thread.sleep(1000);
                    System.out.println("thread A do wait");
                    // 带timeOut的wait超时后，该线程会自动加入锁池
                    lock.wait(1000);// 线程处于timed waiting状态
                    System.out.println("thread A is done");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            System.out.println("thread B is waiting to get locks");
            synchronized (lock) {
                System.out.println("thread B get locks");
                try {
                    System.out.println("thread B is running");
                    // notifyAll将等待池中线程放入锁池
                    // 并不会释放锁，只有持有锁的线程（当前线程）run方法完成或者抛异常，才会释放锁
                    lock.notifyAll();
                    // yield不会对锁有任何影响
                    Thread.yield();
                    // sleep并不会释放锁，会让出cpu
                    Thread.sleep(5000);
                    System.out.println("thread B done");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}