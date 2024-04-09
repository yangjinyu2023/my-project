package com.example.demo.datastructure.queue;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 单调队列
 *
 * @author yangjinyu
 * @time 2022/8/6 14:42
 */
public class MonotonicQueue<E extends Comparable<E>> {
    // 实现一个队列，既可以保证出入队的顺序，还可以获取最值（这里实现最大值）
    // 优先级队列会丢失出入队顺序，poll()提供的是最值
    // 要点：一个普通队列，维护出入队顺序；一个双端队列，维护单调队列
    // 进队时间复杂度O(1)，出队均摊时间复杂度O(1)，取最大值时间复杂度0(1)，空间复杂度O(2n)
    private final Queue<E> queue;
    private final Deque<E> deque;

    public MonotonicQueue() {
        queue = new LinkedList<>();
        deque = new LinkedList<>();
    }

    public void offer(E e) {
        queue.offer(e);
        while (!deque.isEmpty() && e.compareTo(deque.peekLast()) > 0) {
            deque.pollLast();
        }
        deque.offerLast(e);
    }

    public E poll() {
        if (queue.isEmpty() || deque.isEmpty()) {
            return null;
        }
        if (queue.peek().compareTo(deque.peekFirst()) == 0) {
            deque.pollFirst();
        }
        return queue.poll();
    }

    public E max() {
        if (deque.isEmpty()) {
            return null;
        }
        return deque.peekFirst();
    }

    public static void main(String[] args) {
        MonotonicQueue<Integer> queue = new MonotonicQueue<>();
        queue.offer(5);
        System.out.println("offer(5)");
        System.out.println("max is " + queue.max());
        queue.offer(3);
        System.out.println("offer(3)");
        System.out.println("max is " + queue.max());
        queue.offer(4);
        System.out.println("offer(4)");
        System.out.println("max is " + queue.max());
        queue.offer(8);
        System.out.println("offer(8)");
        System.out.println("max is " + queue.max());
        queue.offer(2);
        System.out.println("offer(2)");
        System.out.println("max is " + queue.max());
        queue.offer(7);
        System.out.println("offer(7)");
        System.out.println("max is " + queue.max());
        System.out.println("poll " + queue.poll());
        System.out.println("max is " + queue.max());
        System.out.println("poll " + queue.poll());
        System.out.println("max is " + queue.max());
        System.out.println("poll " + queue.poll());
        System.out.println("max is " + queue.max());
        System.out.println("poll " + queue.poll());
        System.out.println("max is " + queue.max());
        System.out.println("poll " + queue.poll());
        System.out.println("max is " + queue.max());
        System.out.println("poll " + queue.poll());
        System.out.println("max is " + queue.max());
    }
}