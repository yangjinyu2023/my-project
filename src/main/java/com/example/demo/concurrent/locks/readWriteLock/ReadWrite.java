package com.example.demo.concurrent.locks.readWriteLock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @description:
 * @author: yangjinyu
 * @time: 2020/2/1 20:21
 */
public class ReadWrite implements Runnable {

    ReentrantReadWriteLock readWriteLock;

    public ReadWrite(ReentrantReadWriteLock readWriteLock){
        this.readWriteLock = readWriteLock;
    }

    @Override
    public void run() {
        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "开始读取文件");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            System.out.println(Thread.currentThread().getName() + "读取完成");
            readWriteLock.readLock().unlock();
        }
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "开始写入");
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            System.out.println(Thread.currentThread().getName() + "写入完成");
            readWriteLock.writeLock().unlock();
        }

    }
}