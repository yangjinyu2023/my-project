package com.example.demo.concurrent.locks.reetrantLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

public class InterruptedRunnable implements Runnable {
    Lock firstLock;
    Lock secondLock;

    public InterruptedRunnable(Lock firstLock, Lock secondLock) {
        this.firstLock = firstLock;
        this.secondLock = secondLock;
    }

    @Override
    public void run() {
        try {
            // locks 与 lockInterruptibly比较区别在于：
            // locks 优先考虑获取锁，待获取锁成功后，才响应中断
            // lockInterruptibly优先考虑响应中断，而不是响应锁的普通获取或重入获取
            // lockInterruptibly允许在等待时，由其它线程调用等待线程的Thread.interrupt方法来中断等待线程的等待而直接返回
            // 这时不用获取锁，而会抛出一个InterruptedException。
            // lock方法不允许Thread.interrupt中断，即使检测到Thread.isInterrupted,一样会继续尝试获取锁，失败则继续休眠。
            // 只是在最后获取锁成功后再把当前线程置为interrupted状态，然后再中断线程。


            firstLock.lockInterruptibly();
            System.out.println(Thread.currentThread().getName() + " get locks: " + firstLock.toString());

            TimeUnit.MILLISECONDS.sleep(100);//更好的触发死锁

            secondLock.lockInterruptibly();
            System.out.println(Thread.currentThread().getName() + " get locks: " + secondLock.toString());

        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " is interrupted");
        } finally {
            firstLock.unlock();
            secondLock.unlock();
            System.out.println(Thread.currentThread().getName() + " end successful");
        }
    }
}