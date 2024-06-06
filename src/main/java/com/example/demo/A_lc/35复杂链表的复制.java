package com.example.demo.A_lc;
//请实现 copyRandomList 函数，复制一个复杂链表。在复杂链表中，每个节点除了有一个 next 指针指向下一个节点，还有一个 random 指针指
//向链表中的任意节点或者 null。 
//
// 
//
// 示例 1： 
//
// 
//
// 输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
//输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
// 
//
// 示例 2： 
//
// 
//
// 输入：head = [[1,1],[2,1]]
//输出：[[1,1],[2,1]]
// 
//
// 示例 3： 
//
// 
//
// 输入：head = [[3,null],[3,0],[3,null]]
//输出：[[3,null],[3,0],[3,null]]
// 
//
// 示例 4： 
//
// 输入：head = []
//输出：[]
//解释：给定的链表为空（空指针），因此返回 null。
// 
//
// 
//
// 提示： 
//
// 
// -10000 <= Node.val <= 10000 
// Node.random 为空（null）或指向链表中的节点。 
// 节点数目不超过 1000 。 
// 
//
// 
//
// 注意：本题与主站 138 题相同：https://leetcode-cn.com/problems/copy-list-with-random-
//pointer/ 
//
// 
// Related Topics 哈希表 链表 👍 555 👎 0


import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)


class Solution35 {
    // Definition for a Node.
    static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        // 把新老node的映射关系存下来，这样new->old->oldrandom->newrandom
        // 写法复杂，不够精炼
        /*
        Map<Node, Node> old_new = new HashMap<>();
        Map<Node, Node> new_old = new HashMap<>();
        Node newHead = new Node(head.val);
        old_new.put(head, newHead);
        new_old.put(newHead, head);
        Node node = head.next, newNode = newHead;
        while (node != null) {
            newNode.next = new Node(node.val);
            old_new.put(node, newNode.next);
            new_old.put(newNode.next, node);
            node = node.next;
            newNode = newNode.next;
        }
        newNode = newHead;
        while(newNode != null){
            Node random = new_old.get(newNode).random;
            if(random != null) {
                newNode.random = old_new.get(random);
            }else{
                newNode.random = null;
            }
            newNode = newNode.next;
        }
        return newHead;
        */
        // solutions第二个，解法1，hash表
        // 思路和自己大概一致，只不过一个map就够，而且对新节点指针的赋值放在第二个循环中
        // old->new，oldrandom->newrandom，然后new.random = newrandom
        Map<Node, Node> map = new HashMap<>();
        Node node = head;
        while(node != null){
            map.put(node, new Node(node.val));
            node = node.next;
        }
        node = head;
        while(node != null){
            // map.get(null)==null
            map.get(node).next = map.get(node.next);
            map.get(node).random = map.get(node.random);
            node = node.next;
        }
        return map.get(head);// 找到newHead
    }
}
//leetcode submit region end(Prohibit modification and deletion)
