package com.example.demo.A_lc; /**
 * 输入一棵二叉树的根节点，求该树的深度。从根节点到叶节点依次经过的节点（含根、叶节点）形成树的一条路径，最长路径的长度为树的深度。
 * <p>
 * 例如：
 * <p>
 * 给定二叉树 [3,9,20,null,null,15,7]，
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * <p>
 * 返回它的最大深度 3 。
 * <p>
 * <p>
 * <p>
 * 提示：
 * <p>
 * <p>
 * 节点总数 <= 10000
 * <p>
 * <p>
 * 注意：本题与主站 104 题相同：https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/
 * <p>
 * <p>
 * Related Topics 树 深度优先搜索 广度优先搜索 二叉树 👍 198 👎 0
 */

//leetcode submit region begin(Prohibit modification and deletion)

import java.util.LinkedList;
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
class Solution55I {
    // 使用BFS或DFS统计数的深度，一定要记住！
    public int maxDepth(TreeNode root) {
        // DFS，树的最大深度，为左子树的最大深度和右子树的最大深度中的较大值加1
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
        //return maxDepth2(root);
    }

    public int maxDepth2(TreeNode root) {
        // BFS，遍历完每层，深度加1
        if (root == null) {
            return 0;
        }
        int level = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            for (int i = queue.size(); i > 0; i--) {// 关键点在这里，剑指offer32题也是
                root = queue.poll();
                if (root.left != null) {
                    queue.offer(root.left);
                }
                if (root.right != null) {
                    queue.offer(root.right);
                }
            }
            level++;
        }
        return level;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
