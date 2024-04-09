package com.example.demo.datastructure.queue;

import com.example.demo.datastructure.heap.Heap;

/**
 * 优先级队列
 * <p>
 * 优先级队列可以用有序数组来实现，删除最大数据项的时间复杂度为O(1)，插入时间复杂度为 O(N);
 * 优先级队列可以用堆来实现，删除最大数据项的时间复杂度为O(logN)，插入时间复杂度为O(logN)；
 * 有很多插入操作时，可以选择用堆来实现优先级队列。
 * </p>
 *
 * @author yangjinyu
 * @time 2021/6/8 14:32
 */
public class PriorityQueue<E extends Comparable<? super E>> {
    // 使用堆实现优先级队列
    private Heap<E> heap;

    public PriorityQueue(int capacity) {
        this.heap = new Heap<>(capacity);
    }

    public void insert(E e) {
        heap.insert(e);
    }

    public E remove() {
        return heap.remove();
    }

    public static void main(String[] args) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(10);
        queue.insert(40);
        queue.insert(100);
        queue.insert(5);
        System.out.println(queue.remove());
        System.out.println(queue.remove());
        System.out.println(queue.remove());
    }
}