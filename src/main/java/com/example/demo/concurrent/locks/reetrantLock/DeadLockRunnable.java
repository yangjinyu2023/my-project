package com.example.demo.concurrent.locks.reetrantLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

public class DeadLockRunnable implements Runnable {
    Lock firstLock;
    Lock secondLock;

    public DeadLockRunnable(Lock firstLock, Lock secondLock) {
        this.firstLock = firstLock;
        this.secondLock = secondLock;
    }

    @Override
    public void run() {
        // tryLock()可以选择传入时间参数，表示等待指定的时间，成功获取返回true（此时会获取锁），超时未获取返回false
        // 无参则表示立即返回锁申请的结果：true表示获取锁成功，false表示获取锁失败
        try {
            while (!firstLock.tryLock(10, TimeUnit.SECONDS)) {
                TimeUnit.MILLISECONDS.sleep(10);
            }
            System.out.println(Thread.currentThread().getName() + " get locks " + firstLock.toString());
            while (!secondLock.tryLock(10, TimeUnit.SECONDS)) {
                firstLock.unlock();
                TimeUnit.MILLISECONDS.sleep(10);
            }
            System.out.println(Thread.currentThread().getName() + " get locks " + secondLock.toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            firstLock.unlock();
            secondLock.unlock();
            System.out.println(Thread.currentThread().getName() + "正常结束!");
        }
    }
}