package com.example.demo.A_lc; /**
 * 输入一棵二叉树的根节点，判断该树是不是平衡二叉树。如果某二叉树中任意节点的左右子树的深度相差不超过1，那么它就是一棵平衡二叉树。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 给定二叉树 [3,9,20,null,null,15,7]
 * <p>
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * <p>
 * 返回 true 。 示例 2:
 * <p>
 * 给定二叉树 [1,2,2,3,3,null,null,4,4]
 * <p>
 * <p>
 * 1
 * / \
 * 2   2
 * / \
 * 3   3
 * / \
 * 4   4
 * <p>
 * <p>
 * 返回 false 。
 * <p>
 * <p>
 * <p>
 * 限制：
 * <p>
 * <p>
 * 0 <= 树的结点个数 <= 10000
 * <p>
 * <p>
 * 注意：本题与主站 110 题相同：https://leetcode-cn.com/problems/balanced-binary-tree/
 * <p>
 * <p>
 * <p>
 * Related Topics 树 深度优先搜索 二叉树 👍 294 👎 0
 */

//leetcode submit region begin(Prohibit modification and deletion)

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public boolean isBalanced(TreeNode root) {
        // 任意左右子树深度相差不超过1，这种容易想，可以记忆它
        // 此解法基于上题求树深度，进而判断是否为平衡二叉树，但是产生了重复判断
        if (root == null) {
            return true;
        }
        if (Math.abs(depth1(root.left) - depth1(root.right)) > 1) {
            return false;
        }
        return isBalanced(root.left) && isBalanced(root.right);
        // 这种是比较好的写法
        //return depth2(root) != -1;//（-1代表不平衡）
    }

    public int depth1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(depth1(root.left), depth1(root.right)) + 1;
    }

    public int depth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 如果任意左右子树已经不平衡，直接返回-1（剪枝）
        int leftDepth = depth2(root.left);
        if (leftDepth == -1) {
            return -1;
        }
        int rightDepth = depth2(root.right);
        if (rightDepth == -1) {
            return -1;
        }
        // 如果左右子树都平衡，判断以root为根的子树是否平衡，不平衡返回-1（剪枝）
        if (Math.abs(leftDepth - rightDepth) > 1) {
            return -1;
        }
        // 如果平衡，返回以root为根的子树深度
        return Math.max(depth2(root.left), depth2(root.right)) + 1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
