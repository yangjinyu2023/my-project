package com.example.demo.concurrent.locks.readWriteLock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockDemo {

    public static void main(String[] args) {

        System.out.println(Long.toBinaryString((1 << 16) - 1));
        System.out.println(Long.toBinaryString(1 & (1 << 16) - 1));
        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        //readWriteLock.readLock().lock();
        //System.out.println(11);
        readWriteLock.writeLock().lock();
        System.out.println(22);
        readWriteLock.readLock().lock();
        System.out.println(33);
        readWriteLock.writeLock().lock();
        System.out.println(44);
        readWriteLock.readLock().lock();
        System.out.println(55);
        readWriteLock.writeLock().unlock();
        readWriteLock.writeLock().unlock();
        readWriteLock.readLock().unlock();
        readWriteLock.readLock().unlock();
        //readWriteLock.readLock().unlock();
        System.out.println(66);

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new ReadWrite(readWriteLock));
        executorService.execute(new ReadWrite(readWriteLock));
        executorService.execute(new ReadWrite(readWriteLock));

        executorService.shutdown();
    }
}