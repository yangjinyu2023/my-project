package com.example.demo.datastructure.linkedlist;

/**
 * 双端链表（注意不是双向链表）
 *
 * @author yangjinyu
 * @time 2021/6/9 14:36
 */
public class DoublePointLinkedList<E> {
    Node<E> head;
    Node<E> tail;
    int size;

    public DoublePointLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    private static class Node<E> {
        E item;
        Node<E> next;

        public Node(E item) {
            this.item = item;
        }
    }

    public void addHead(E e) {
        Node<E> node = new Node<>(e);
        if (size == 0) {
            head = node;
            tail = node;
        } else {
            node.next = head;
            head = node;
        }
        size++;
    }

    public void addTail(E e) {
        Node<E> node = new Node<>(e);
        if (size == 0) {
            head = node;
        } else {
            tail.next = node;
        }
        tail = node;
        size++;
    }


    public E deleteHead() {
        if (size > 0) {
            Node<E> node = head;
            head = head.next;
            // 避免垃圾无法回收，注意
            if (head == null) {
                tail = null;
            }
            size--;
            return node.item;
        }
        return null;
    }

    //显示节点信息
    public void display() {
        if (size > 0) {
            Node<E> node = head;
            while (node != null) {
                System.out.print(node.item);
                node = node.next;
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        // 双端链表模拟队列
        DoublePointLinkedList<String> list = new DoublePointLinkedList<>();
        list.addTail("3");
        list.addTail("1");
        list.addTail("5");
        list.display();
        System.out.println(list.deleteHead());
        System.out.println(list.deleteHead());
        System.out.println(list.deleteHead());
    }
}