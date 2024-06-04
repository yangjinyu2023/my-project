package com.example.demo.A_lc;
//输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。
//
// 例如，一个链表有 6 个节点，从头节点开始，它们的值依次是 1、2、3、4、5、6。这个链表的倒数第 3 个节点是值为 4 的节点。 
//
// 
//
// 示例： 
//
// 
//给定一个链表: 1->2->3->4->5, 和 k = 2.
//
//返回链表 4->5. 
// Related Topics 链表 双指针 👍 373 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
 * }
 */
class Solution22 {
    public ListNode getKthFromEnd(ListNode head, int k) {
        // 双指针，先让p2跳，两者距离k-1了，p1和p2一起跳
        ListNode p1 = head, p2 = head;
        int p2Moved = 0;
        while (p2.next != null) {
            if (p2Moved >= k - 1) {
                p1 = p1.next;
            }
            p2 = p2.next;
            p2Moved++;
        }
        return p1;
    }

    public ListNode getKthFromEnd111(ListNode head, int k) {
        // 1->2->3->4->5
        ListNode node1 = head, node2 = head;
        // 先让node2移动k次
        for(int i = 0; i < k; i++) {
            if(node2 == null) return null; // 如果k大于链表长度，则返回null
            node2 = node2.next;
        }
        // 然后node1和node2同时移动，直到node2到达链表末尾
        while(node2 != null) {
            node1 = node1.next;
            node2 = node2.next;
        }
        // 此时node1指向倒数第k个节点
        return node1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
