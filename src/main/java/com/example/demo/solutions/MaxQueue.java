package com.example.demo.solutions;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

class MaxQueue {

    // 5436217
    public static void main(String[] args) {
        MaxQueue queue = new MaxQueue();
        queue.push_back(9);
        queue.push_back(5);
        queue.push_back(4);
        queue.push_back(3);
        queue.push_back(9);
        queue.push_back(6);
        queue.push_back(2);
        queue.push_back(1);
        queue.push_back(7);
        System.out.println(queue.pop_front());
        System.out.println(queue.pop_front());
        System.out.println(queue.pop_front());
        System.out.println(queue.pop_front());
        System.out.println(queue.pop_front());
        System.out.println(queue.pop_front());
        System.out.println(queue.pop_front());
        System.out.println(queue.pop_front());
        System.out.println(queue.pop_front());
    }
    Queue<Integer> queue;//正常队列，注意用的是Queue<Integer>，而不是LinkedList<Integer>。
    Deque<Integer> deque;//双向队列，注意用的是Deque<Integer>，而不是LinkedList<Integer>。

    /**
     * 1. 此时 queue 虽然用的是LinkedList，也尽量用queue的方法，而不是用LinkedList的方法，
     * 因为queue定义时用的是Queue<Integer>，而不是LinkedList<Integer>。故要用offer，peek，poll，
     * 而不是addLast，getFirst，removeFirst。
     *
     * 2. 此时 deque 虽然用的是LinkedList，也尽量用Deque的方法，而不是用LinkedList的方法，
     * 因为deque定义时用的是Deque<Integer>，而不是LinkedList<Integer>。故要用offerLast，peekLast，
     * pollLast，peekFirst，pollFirst。而不是addLast,getLast,removeLast,getFirst,removeFirst。
     */
    public MaxQueue() {
        queue = new LinkedList<>();
        deque = new LinkedList<>();
    }

    public int max_value() {
        if(deque.isEmpty()){
            return -1;
        }
        return deque.peekFirst();
    }

    public void push_back(int value) {
        while(!deque.isEmpty() && deque.peekLast() < value){
            deque.pollLast();
        }
        deque.offerLast(value);
        queue.offer(value);
    }

    public int pop_front() {
        if (queue.isEmpty()) {
            return -1;
        }
        if(queue.peek().equals(deque.peekFirst())){
            deque.pollFirst();
        }
        return queue.poll();
    }
}

/**
 * Your MaxQueue object will be instantiated and called as such:
 * MaxQueue obj = new MaxQueue();
 * int param_1 = obj.max_value();
 * obj.push_back(value);
 * int param_3 = obj.pop_front();
 */
