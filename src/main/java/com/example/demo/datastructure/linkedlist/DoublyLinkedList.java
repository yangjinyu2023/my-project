package com.example.demo.datastructure.linkedlist;

/**
 * 双向链表
 *
 * @author yangjinyu
 * @time 2021/6/9 14:36
 */
public class DoublyLinkedList<E> {
    Node<E> head;
    Node<E> tail;
    int size;

    public DoublyLinkedList() {
        this.head = null;
        this.size = 0;
    }

    private static class Node<E> {
        E item;
        Node<E> prev;
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
            head.prev = node;
            head = node;
        }
        size++;
    }

    public E deleteHead() {
        if (size > 0) {
            Node<E> node = head;
            head = head.next;
            if (head != null) {
                head.prev = null;
            } else {
                tail = null;// 注意
            }
            size--;
            return node.item;
        }
        return null;
    }

    public void addTail(E e) {
        Node<E> node = new Node<>(e);
        if (size == 0) {
            head = node;
        } else {
            tail.next = node;
            node.prev = tail;
        }
        tail = node;
        size++;
    }

    public E deleteTail() {
        if (size > 0) {
            Node<E> node = tail;
            tail = tail.prev;
            if (tail != null) {
                tail.next = null;
            } else {
                head = null;
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
        DoublyLinkedList<String> list = new DoublyLinkedList<>();
        list.addHead("1");
        list.display();
        list.addHead("2");
        list.display();
        list.addTail("3");
        list.display();
        list.addTail("4");
        list.display();
        list.deleteHead();
        list.display();
        list.deleteTail();
        list.display();
        list.deleteTail();
        list.display();
        list.deleteTail();
        list.display();
    }
}