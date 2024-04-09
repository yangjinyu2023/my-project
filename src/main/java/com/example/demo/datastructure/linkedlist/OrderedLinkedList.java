package com.example.demo.datastructure.linkedlist;

/**
 * 有序链表
 *
 * @author yangjinyu
 * @time 2021/6/9 14:36
 */
public class OrderedLinkedList<E extends Comparable<E>> {
    Node<E> head;
    int size;

    public OrderedLinkedList() {
        this.head = null;
        this.size = 0;
    }

    private static class Node<E> {
        E item;
        Node<E> next;

        public Node(E item) {
            this.item = item;
        }
    }

    private void insert(E e) {
        Node<E> node = new Node<>(e);
        if (size > 0) {
            Node<E> previous = head;
            Node<E> current = head;
            while (current != null && e.compareTo(current.item) > 0) {
                previous = current;
                current = current.next;
            }
            if (current == head) {
                node.next = head;
                head = node;
            } else {
                previous.next = node;
                node.next = current;
            }
        } else {
            head = node;
        }
        size++;
    }


    //显示节点信息
    public void display() {
        if (size > 0) {
            Node<E> node = head;
            while (node != null) {
                System.out.print(" " + node.item);
                node = node.next;
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        OrderedLinkedList<Integer> list = new OrderedLinkedList<>();
        list.insert(9);
        list.insert(-3);
        list.insert(7);
        list.insert(11);
        list.insert(99);
        list.insert(-12);
        list.display();
    }
}