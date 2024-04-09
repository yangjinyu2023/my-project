package com.example.demo.algorithm;

import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 146，手写双向链表实现（面试官希望我们完成的是这种）
 *
 * @author yangjinyu
 * @time 2022/10/1 14:29
 */
class LRUCache_Leetcode {

    public static void main(String[] args) {
        // ["LRUCache","put","put","get","put","get","put","get","get","get"]
        // [[2],[1,1],[2,2],[1],[3,3],[2],[4,4],[1],[3],[4]]
        LRUCache_Leetcode lru = new LRUCache_Leetcode(2);
        lru.put(1, 1);
        lru.print();
        lru.put(2, 2);
        lru.print();
        lru.get(1);
        lru.print();
        lru.put(3, 3);
        lru.print();
        lru.get(2);
        lru.print();
        lru.put(4, 4);
        lru.print();
        lru.get(1);
        lru.print();
        lru.get(3);
        lru.print();
        lru.get(4);
        lru.print();
        System.out.println();
    }

    static class Node {
        int value;

        int key;

        Node prev;

        Node next;

        Node() {

        }

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private int capacity;

    private int size;

    private Map < Integer, Node > hash;

    private Node head;

    private Node tail;

    // 两个要注意的点：
    // 1、head和tail常驻
    // 2、先写大框，再写方法细节
    public LRUCache_Leetcode(int capacity) {
        this.capacity = capacity;
        this.hash = new HashMap <>();
        this.head = new Node();
        this.tail = new Node();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        Node node = hash.get(key);
        if (node != null) {
            moveToHead(node);// 先定义出moveToHead方法，再去实现，避免陷入细节，导致越写越着急
            return node.value;
        }
        return -1;
    }

    public void put(int key, int value) {
        Node node = hash.get(key);
        if (node != null) {
            node.value = value;
            moveToHead(node);
        }
        else {
            node = new Node(key, value);
            hash.put(key, node);
            addHead(node);
            size++;
            if (size > capacity) {
                hash.remove(removeTail().key);// 这里一定要注意，hashmap.remove传入的是key，所以Node里要有key
                size--;
            }
        }
    }

    private void moveToHead(Node node) {
        removeNode(node);
        addHead(node);
    }

    private void addHead(Node node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private Node removeTail() {
        Node node = tail.prev;
        removeNode(node);
        return node;
    }

    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public void print() {
        Node node = head;
        while (node.next != null) {
            if (node != head && node != tail) {
                System.out.print(node.value);
            }
            node = node.next;
        }
        System.out.println();
    }
}