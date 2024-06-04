package com.example.demo.A_lc;
//给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。
//
// 返回删除后的链表的头节点。 
//
// 注意：此题对比原题有改动 
//
// 示例 1: 
//
// 输入: head = [4,5,1,9], val = 5
//输出: [4,1,9]
//解释: 给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
// 
//
// 示例 2: 
//
// 输入: head = [4,5,1,9], val = 1
//输出: [4,5,9]
//解释: 给定你链表中值为 1 的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9.
// 
//
// 
//
// 说明： 
//
// 
// 题目保证链表中节点的值互不相同 
// 若使用 C 或 C++ 语言，你不需要 free 或 delete 被删除的节点 
// 
// Related Topics 链表 👍 232 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
 * }
 */
class Solution18 {
    public ListNode deleteNode(ListNode head, int val) {
        ListNode prev = head, current = head;
        while (current != null && current.val != val) {
            prev = current;
            current = current.next;
        }
        // current==null说明没找到
        if (current != null) {
            if (current == head) {
                return current.next;
            } else {
                prev.next = current.next;
            }
        }
        return head;
    }


    public ListNode deleteNode111(ListNode head, int val) {
        // 考虑要找的节点是head的情况，直接返回head.next
        if (head.val == val) {
            return head.next;
        }
        // 非head节点的情况，需要prev、current
        // next作为临时变量即可，和反转链表类似
        ListNode prev = head, current = head;
        while (current != null) {
            ListNode next = current.next;
            if (current.val == val) {
                prev.next = next;
                return head;
            }
            prev = current;
            current = current.next;
        }
        return head;
    }

    public ListNode reverseListNode(ListNode head) {
        ListNode prev = null, current = head;
        while (current != null) {
            ListNode next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        return prev;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        ListNode newHead = new Solution18().deleteNode111(head, 4);
        System.out.println();
        newHead = new Solution18().reverseListNode(newHead);
        System.out.println();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
