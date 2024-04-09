package com.example.demo.datastructure.linkedlist;

/**
 * 单向链表
 *
 * @author yangjinyu
 * @time 2021/6/9 14:36
 */
public class SingleLinkedList<E> {
    Node<E> head;
    int size;

    public SingleLinkedList() {
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

    // addHead和deleteHead，实现栈
    public void addHead(E e) {
        Node<E> node = new Node<>(e);
        if (size != 0) {
            node.next = head;
        }
        head = node;
        size++;
    }

    public E deleteHead() {
        if (size > 0) {
            Node<E> node = head;
            head = head.next;
            size--;
            return node.item;
        }
        return null;
    }

    //查找指定元素，找到了返回节点Node，找不到返回null
    public Node<E> find(Object obj) {
        Node<E> current = head;
        int tempSize = size;
        while (tempSize > 0) {
            if (obj.equals(current.item)) {
                return current;
            } else {
                current = current.next;
            }
            tempSize--;
        }
        return null;
    }


    /**
     * <p>先定位，后删除</p>
     * <p>如果直接删除，无法找到前置节点</p>
     */
    public void delete(E e) {
        if (size > 0) {
            Node<E> previous = head;
            Node<E> current = head;
            while (!current.item.equals(e)) {
                previous = current;
                current = current.next;
                // 没找到直接返回
                if (current == null) {
                    return;
                }
            }
            // 删除时区分head和非head两种情况，因为需要移动head指针
            if (current == head) {
                head = head.next;
            } else {
                previous.next = current.next;
            }
            size--;
        }
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
        SingleLinkedList<String> list = new SingleLinkedList<>();
        list.addHead("1");
        list.addHead("2");
        list.addHead("3");
        list.addHead("4");
        list.addHead("1");
        list.display();
        System.out.println("delete head " + list.deleteHead());
        list.display();
        System.out.println("delete 3");
        list.delete("3");
        list.display();
        System.out.println("delete 4");
        list.delete("4");
        list.display();
    }
}