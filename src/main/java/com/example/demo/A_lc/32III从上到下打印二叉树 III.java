package com.example.demo.A_lc;
//请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推。
//
// 
//
// 例如: 
//给定二叉树: [3,9,20,null,null,15,7], 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7
// 
//
// 返回其层次遍历结果： 
//
// [
//  [3],
//  [20,9],
//  [15,7]
//]
// 
//
// 
//
// 提示： 
//
// 
// 节点总数 <= 1000 
// 
// Related Topics 树 广度优先搜索 二叉树 👍 236 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
class Solution32III {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) {
            queue.offer(root);
        }
        int level = 1;
        while (!queue.isEmpty()) {
            LinkedList<Integer> list = new LinkedList<>();
            // 每次将一层出队，下一层会全部进队
            // 这里queue是在变化的，所以不能(int i = 0; i > queue.size(); i++)
            for (int i = queue.size(); i > 0; i--) {
                TreeNode node = queue.poll();
                // 奇数层从左到右，否则从右到左
                if ((level & 1) != 0) {
                    list.addLast(node.val);// 等价于add
                } else {
                    list.addFirst(node.val);
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            result.add(list);
            level++;
        }
        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
