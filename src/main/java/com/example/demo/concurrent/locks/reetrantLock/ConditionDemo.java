package com.example.demo.concurrent.locks.reetrantLock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionDemo {
    static ReentrantLock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();
    public static void main(String[] args) throws InterruptedException {

        lock.lock();// 加锁
        new Thread(new SignalThread()).start();
        System.out.println("主线程等待通知");
        try {
            condition.await();// 等待唤醒，类似Object wait()
        } finally {
            lock.unlock();// 释放锁
        }
        System.out.println("主线程恢复运行");
    }
    static class SignalThread implements Runnable {
        @Override
        public void run() {
            lock.lock();// 加锁
            try {
                condition.signal();// 唤醒一个等待锁的线程，类似Object notify()
                // condition.signalAll();// 唤醒所有等待锁的线程，类似Object notifyAll()
                System.out.println("子线程通知");
            } finally {
                lock.unlock();// 释放锁
            }
        }
    }
}