package com.example.demo.concurrent.locks.condition;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MyBlockingQueue<E> {

    private final int size;

    private final LinkedList<E> list;

    private final ReentrantLock lock;

    private final Condition notEmpty;

    private final Condition notFull;

    public MyBlockingQueue(int size) {
        this.size = size;
        if(size <= 0)
            throw new IllegalArgumentException();
        list = new LinkedList<>();
        lock = new ReentrantLock();
        notEmpty = lock.newCondition();
        notFull = lock.newCondition();
    }

    public void put(E e) throws InterruptedException {
        lock.lock();
        try {
            while (list.size() == size) {
                notFull.await();
            }
            list.add(e);
            System.out.println(" put e " + e.toString());
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public E take() throws InterruptedException {
        E e;
        lock.lock();
        try {
            while (list.isEmpty()) {
                notEmpty.await();
            }
            e = list.removeFirst();
            System.out.println(" take e " + e.toString());
            notFull.signal();
        } finally {
            lock.unlock();
        }
        return e;
    }


}