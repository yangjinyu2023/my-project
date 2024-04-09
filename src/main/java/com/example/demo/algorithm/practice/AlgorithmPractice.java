package com.example.demo.algorithm.practice;

/**
 * 已刷过的题
 *
 * @author yangjinyu
 * @time 2022/10/25 10:14
 */
public class AlgorithmPractice {

    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    /**
     * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
     * <p>
     * 请你将两个数相加，并以相同形式返回一个表示和的链表。
     * <p>
     * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     * <p>
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * <p>
     * 输入：l1 = [2,4,3], l2 = [5,6,4]
     * 输出：[7,0,8]
     * 解释：342 + 465 = 807.
     * <p>
     * <p>
     * 示例 2：
     * <p>
     * <p>
     * 输入：l1 = [0], l2 = [0]
     * 输出：[0]
     * <p>
     * <p>
     * 示例 3：
     * <p>
     * <p>
     * 输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
     * 输出：[8,9,9,9,0,0,0,1]
     * <p>
     * <p>
     * <p>
     * <p>
     * 提示：
     * <p>
     * <p>
     * 每个链表中的节点数在范围 [1, 100] 内
     * 0 <= Node.val <= 9
     * 题目数据保证列表表示的数字不含前导零
     * <p>
     * <p>
     * Related Topics 递归 链表 数学 👍 8447 👎 0
     */
    public static void main(String[] args) {
    }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode();
        ListNode l3 = head;
        boolean addBit = false;
        while(l1 != null || l2 != null){
            int sum = 0;
            if(l1 != null){
                sum += l1.val;
                l1 = l1.next;
            }
            if(l2 != null){
                sum += l2.val;
                l2 = l2.next;
            }
            if(addBit){
                sum ++;
            }
            if(sum >= 10){
                sum = sum % 10;
                addBit = true;
            }else{
                addBit = false;
            }
            l3.next = new ListNode(sum);
            l3 = l3.next;
        }
        if(addBit){
            l3.next = new ListNode(1);
        }
        return head.next;
    }
}