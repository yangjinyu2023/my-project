package com.example.demo.A_lc;//输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
//
// 
//
// 示例 1： 
//
// 输入：head = [1,3,2]
//输出：[2,3,1] 
//
// 
//
// 限制： 
//
// 0 <= 链表长度 <= 10000 
// Related Topics 栈 递归 链表 双指针 👍 310 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.Stack;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
 * }
 */
class ListNode {
  int val;
  ListNode next;
  ListNode(int x) { val = x; }
}
class Solution6 {
    public int[] reversePrint(ListNode head) {
        // 时间复杂度O(n)，空间复杂度O(n)
        Stack<ListNode> stack = new Stack<ListNode>();
        ListNode temp = head;
        while (temp != null) {
            stack.push(temp);
            temp = temp.next;
        }
        int size = stack.size();
        int[] print = new int[size];
        for (int i = 0; i < size; i++) {
            print[i] = stack.pop().val;
        }
        return print;
        /*
        if(head != null){
            Stack<Integer> stack = new Stack<>();
            stack.push(head.val);
            ListNode nextNode = head.next;
            while (nextNode != null){
                stack.push(nextNode.val);
                nextNode = nextNode.next;
            }
            int[] array = new int[stack.size()];
            for (int i = 0; !stack.isEmpty(); i++) {
                array[i] = stack.pop();
            }
            return array;
        }
        return new int[0];
        */
    }
}
//leetcode submit region end(Prohibit modification and deletion)
