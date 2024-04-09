package com.example.demo.algorithm.interview;

/**
 * 给定一个都是数字的字符串，形成一个链表，每个节点存放一位数字
 *
 * @author yangjinyu
 * @time 2022/9/2 11:44
 */
public class T11 {

    static class Node{
        int val;
        Node next;

        public Node(int val) {
            this.val = val;
        }
    }

    public Node createLinkedList(String str){
        if(str == null || str.length() == 0){
            return null;
        }
        Node head = new Node(str.charAt(0) - '0');
        Node node = head;
        for (int i = 1; i < str.length(); i++) {
            node.next = new Node(str.charAt(i) - '0');
            node = node.next;
        }
        return head;
    }

    public static void main(String[] args) {
        Node node = new T11().createLinkedList("123456");
        while(node != null){
            System.out.println(node.val);
            node = node.next;
        }
    }
}