package com.example.demo.algorithm.interview;

/**
 * 送分题：单链表反转、合并两个有序链表、版本号比较。力扣原题：21、165、206。
 *
 * @author yangjinyu
 * @time 2022/10/18 16:27
 */
public class T1 {

    // 合并两个有序链表 21
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // 创建一个虚拟头节点，避免判空带来的复杂度
        ListNode head = new ListNode();
        // 创建一个指针指向当前节点
        ListNode node = head;
        // 归并排序中，归并的步骤
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                node.next = list1;
                list1 = list1.next;
            }
            else {
                node.next = list2;
                list2 = list2.next;
            }
            node = node.next;
        }
        node.next = list1 != null ? list1 : list2;
        return head.next;
    }

    // 反转链表 206，双指针，关键点是使用一个临时变量存放next
    public ListNode reverseList(ListNode head) {
        // 反转链表必须记住，双指针
        ListNode prev = null;
        ListNode node = head;
        while (node != null) {
            ListNode next = node.next;// 临时变量
            node.next = prev;
            prev = node;
            node = next;
        }
        return prev;// 返回的是prev
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        ListNode node = head.next;
        node.next = new ListNode(3);
        node = node.next;
        node.next = new ListNode(4);
        node = node.next;
        node.next = new ListNode(5);
        head = new T1().reverseList_rec(head);
        System.out.println(head);
    }

    // 递归版本
    public ListNode reverseList_rec(ListNode head) {
        // head为null的情况，就直接返回null
        // head.next为null是结束递归的条件
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseList_rec(head.next);
        // 传入head.next才能让递归“动”起来
        head.next.next = head;
        head.next = null;// 防止原head形成环，因为没人会修改原head的next了
        return newHead;
    }

    // 比较版本号 165 例如：1.0.1 > 1.0.0.2
    public int compareVersion(String version1, String version2) {
        String[] arr1 = version1.split("\\.");
        String[] arr2 = version2.split("\\.");
        for (int i = 0; i < arr1.length || i < arr2.length; i++) {
            int x = 0, y = 0;
            if (i < arr1.length) {
                x = Integer.parseInt(arr1[i]);
            }
            if (i < arr2.length) {
                y = Integer.parseInt(arr2[i]);
            }
            if (x > y) {
                return 1;
            }
            if (x < y) {
                return -1;
            }
        }
        return 0;
    }

    static class ListNode {
        int val;

        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}