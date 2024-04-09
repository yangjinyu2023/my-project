package com.example.demo.algorithm.interview;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 手写ArrayBlockingQueue
 *
 * @author yangjinyu
 * @time 2022/10/19 17:08
 */
public class T6 {
    private int capacity;

    private int size, head, tail;

    private ReentrantLock lock;

    private Condition notFull;

    private Condition notEmpty;

    private Object[] arr;

    public T6(int capacity) {
        this.capacity = capacity;
        this.arr = new Object[capacity];
        this.lock = new ReentrantLock();
        this.notEmpty = lock.newCondition();
        this.notFull = lock.newCondition();
    }

    /**
     * 移除并返回队列头元素，队列为空则阻塞
     *
     * @return
     */
    public Object take() {
        Object obj = null;
        lock.lock();
        try {
            while (size == 0) {
                notEmpty.await();
            }
            size--;
            obj = arr[head++];
            if (head == capacity) {
                head = 0;
            }
            notFull.signal();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }
        return obj;
    }

    /**
     * 队尾添加一个元素，队列已满阻塞
     *
     * @param obj
     */
    public void put(Object obj) {
        lock.lock();
        try {
            while (size == capacity) {
                notFull.await();
            }
            size++;
            arr[tail++] = obj;
            if (tail == capacity) {
                tail = 0;
            }
            notEmpty.signal();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 移除并返回队列头元素，队列为空则返回null
     *
     * @return
     */
    public Object poll() {
        if (size == 0) {
            return null;
        }
        Object obj = arr[head++];
        if (head == capacity) {
            head = 0;
        }
        size--;
        return obj;
    }

    /**
     * 队尾添加一个元素，成功返回true，队列已满返回false
     *
     * @param obj
     * @return
     */
    public boolean offer(Object obj) {
        if (size == capacity) {
            return false;
        }
        arr[tail++] = obj;
        if (tail == capacity) {
            tail = 0;
        }
        size++;
        return true;
    }

    public static void main(String[] args) {
        T6 t6 = new T6(3);
        System.out.println(t6.offer(1));
        System.out.println(t6.offer(2));
        System.out.println(t6.offer(3));
        System.out.println(t6.offer(4));
        System.out.println(t6.poll());
        System.out.println(t6.poll());
        System.out.println(t6.offer(5));
        System.out.println(t6.poll());
        System.out.println(t6.poll());
        System.out.println(t6.poll());
        System.out.println(t6.offer(6));
        System.out.println(t6.poll());
        System.out.println(t6.size);
    }
}