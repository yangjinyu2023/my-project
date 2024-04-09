package com.example.demo.datastructure.stack;

import java.util.Arrays;

/**
 * 自定义栈
 *
 * @author yangjinyu
 * @time 2021/6/7 20:46
 */
public class MyStack {
    private Object[] array;
    private int capacity;
    private int top;

    public MyStack(int capacity) {
        this.top = -1;
        this.capacity = capacity;
        this.array = new Object[capacity];
    }

    public Object pop() {
        return array[top--];
    }

    public Object peek() {
        return array[top];
    }

    public void push(Object object) {
        if (top >= capacity - 1) {
            resize();
        }
        array[++top] = object;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == capacity - 1;
    }

    private void resize() {
        capacity = capacity << 1;
        array = Arrays.copyOf(array, capacity);
    }

    public static void main(String[] args) {
        MyStack stack = new MyStack(2);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }
}