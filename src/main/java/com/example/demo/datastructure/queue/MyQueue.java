package com.example.demo.datastructure.queue;

/**
 * 自定义队列（循环队列）
 *
 * @author yangjinyu
 * @time 2021/6/8 10:16
 */
public class MyQueue {
    public Object[] array;
    public int front;
    public int rear;
    public int capacity;
    public int size;

    public MyQueue(int capacity) {
        this.capacity = capacity;
        this.array = new Object[capacity];
        this.size = 0;
        this.front = 0;
        this.rear = -1;
    }

    public void insert(Object object) {
        if (!isFull()) {
            if (rear == capacity - 1) {
                rear = -1;
            }
            array[++rear] = object;
            size++;
        }else{
            System.out.println("queue is full!");
        }
    }

    public Object remove() {
        Object object = null;
        if (!isEmpty()) {
            object = array[front];
            array[front] = null;
            if (front == capacity - 1) {
                front = 0;
            } else {
                front++;
            }
            size--;
        }else{
            System.out.println("queue is empty!");
        }
        return object;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public int getSize() {
        return size;
    }

    public static void main(String[] args) {
        MyQueue queue = new MyQueue(5);
        System.out.println("insert 1");
        queue.insert(1);
        System.out.println("capacity" + queue.capacity + "size" + queue.size + "front" + queue.front + "rear" + queue.rear);
        System.out.println("insert 2");
        queue.insert(2);
        System.out.println("capacity" + queue.capacity + "size" + queue.size + "front" + queue.front + "rear" + queue.rear);
        System.out.println("insert 3");
        queue.insert(3);
        System.out.println("capacity" + queue.capacity + "size" + queue.size + "front" + queue.front + "rear" + queue.rear);
        System.out.println("insert 4");
        queue.insert(4);
        System.out.println("capacity" + queue.capacity + "size" + queue.size + "front" + queue.front + "rear" + queue.rear);
        System.out.println("insert 5");
        queue.insert(5);
        System.out.println("capacity" + queue.capacity + "size" + queue.size + "front" + queue.front + "rear" + queue.rear);
        System.out.println("insert 9999");
        queue.insert(9999);
        System.out.println("remove"+queue.remove());
        System.out.println("capacity" + queue.capacity + "size" + queue.size + "front" + queue.front + "rear" + queue.rear);
        System.out.println("insert 6");
        queue.insert(6);
        System.out.println("capacity" + queue.capacity + "size" + queue.size + "front" + queue.front + "rear" + queue.rear);
        System.out.println("insert 8888");
        queue.insert(8888);
        System.out.println("remove"+queue.remove());
        System.out.println("capacity" + queue.capacity + "size" + queue.size + "front" + queue.front + "rear" + queue.rear);
        System.out.println("remove"+queue.remove());
        System.out.println("capacity" + queue.capacity + "size" + queue.size + "front" + queue.front + "rear" + queue.rear);
        System.out.println("insert 7");
        queue.insert(7);
        System.out.println("capacity" + queue.capacity + "size" + queue.size + "front" + queue.front + "rear" + queue.rear);
        System.out.println("insert 8");
        queue.insert(8);
        System.out.println("capacity" + queue.capacity + "size" + queue.size + "front" + queue.front + "rear" + queue.rear);
        System.out.println("-----------------------------");
        int size = queue.size;
        for (int i = 0; i < size; i++) {
            System.out.println(queue.remove());
        }
        System.out.println("capacity" + queue.capacity + "size" + queue.size + "front" + queue.front + "rear" + queue.rear);
        queue.remove();
    }
}