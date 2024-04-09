package com.example.demo.concurrent.locks.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionDemo {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        new Thread(() -> {
            lock.lock();
            try {
                System.out.println(1);
                Thread.sleep(1000);
                condition.signal();
                System.out.println(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }
        }).start();
        try {
            lock.lock();
            System.out.println(3);
            condition.await();// 释放所有锁
            System.out.println(4);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
        System.out.println(5);
    }
}