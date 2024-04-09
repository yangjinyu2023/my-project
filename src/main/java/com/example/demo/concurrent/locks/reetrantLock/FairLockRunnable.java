package com.example.demo.concurrent.locks.reetrantLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class FairLockRunnable implements Runnable {

    private static Lock reEntrantLock = new ReentrantLock(true);

    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            try {
                reEntrantLock.lock();
                System.out.println(Thread.currentThread().getName() + "get locks");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                reEntrantLock.unlock();
            }
        }
    }

}