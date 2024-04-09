package com.example.demo.concurrent.locks.reetrantLock;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {

    static ReentrantLock lock1 = new ReentrantLock();
    static ReentrantLock lock2 = new ReentrantLock();

    // https://www.cnblogs.com/takumicx/p/9338983.html
    public static void main(String[] args) {
        // 公平锁
        //FairLockRunnable task = new FairLockRunnable();
        //Thread thread1 = new Thread(task);
        //Thread thread2 = new Thread(task);
        //thread1.start();
        //thread2.start();

        // ReentrantLock可响应中断
        //Thread thread1 = new Thread(new InterruptedRunnable(lock1, lock2), "thread1");//该线程先获取锁1,再获取锁2
        //Thread thread2 = new Thread(new InterruptedRunnable(lock2, lock1), "thread2");//该线程先获取锁2,再获取锁1
        //thread1.start();
        //thread2.start();
        //thread1.interrupt();//是第一个线程中断

        // 限时等待获取锁
        Thread thread1 = new Thread(new DeadLockRunnable(lock1, lock2), "thread1");//该线程先获取锁1,再获取锁2
        Thread thread2 = new Thread(new DeadLockRunnable(lock2, lock1), "thread2");//该线程先获取锁2,再获取锁1
        Thread thread3 = new Thread(new DeadLockRunnable(lock1, lock2), "thread3");

        thread1.start();
        thread2.start();
        thread3.start();
    }
}