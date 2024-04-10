package com.example.demo.A_lc; /**
 * 给定一棵二叉搜索树，请找出其中第 k 大的节点的值。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * <p>
 * 输入: root = [3,1,4,null,2], k = 1
 * 3
 * / \
 * 1   4
 * \
 *    2
 * 输出: 4
 * <p>
 * 示例 2:
 * <p>
 * <p>
 * 输入: root = [5,3,6,2,4,null,null,1], k = 3
 * 5
 * / \
 * 3   6
 * / \
 * 2   4
 * /
 * 1
 * 输出: 4
 * <p>
 * <p>
 * <p>
 * 限制：
 * <p>
 * <p>
 * 1 ≤ k ≤ 二叉搜索树元素个数
 * <p>
 * <p>
 * Related Topics 树 深度优先搜索 二叉搜索树 二叉树 👍 324 👎 0
 */

//leetcode submit region begin(Prohibit modification and deletion)

import java.util.Stack;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
class Solution54 {
    int res = 0, k = 0, i = 0;

    public int kthLargest(TreeNode root, int k) {
        this.k = k;
        dfs(root);
        return res;
    }

    public void dfs(TreeNode node) {
        if (node == null) {
            return;
        }
        dfs(node.right);// 从大到小排序，先右后左
        if (++i == k) {
            res = node.val;
            return;
        }
        dfs(node.left);
    }

    public void dfs_stack(TreeNode node) {
        // 辅助栈
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                stack.push(node);
                node = node.right;
            } else {
                node = stack.pop();
                if (++i == k) {
                    res = node.val;
                    return;
                }
                node = node.left;
            }
        }
    }

}
//leetcode submit region end(Prohibit modification and deletion)
