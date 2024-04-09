package com.example.demo.A_lc; /**
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 * <p>
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个
 * 节点也可以是它自己的祖先）。”
 * <p>
 * 例如，给定如下二叉树: root = [3,5,1,6,2,0,8,null,null,7,4]
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * 输出: 3
 * 解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
 * <p>
 * <p>
 * 示例 2:
 * <p>
 * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * 输出: 5
 * 解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。
 * <p>
 * <p>
 * <p>
 * <p>
 * 说明:
 * <p>
 * <p>
 * 所有节点的值都是唯一的。
 * p、q 为不同节点且均存在于给定的二叉树中。
 * <p>
 * <p>
 * 注意：本题与主站 236 题相同：https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-
 * binary-tree/
 * <p>
 * Related Topics 树 深度优先搜索 二叉树 👍 464 👎 0
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
    // 与上题同理，从根节点向下搜索（先序遍历），
    // 如果p、q分别居于某结点的左右节点，那么该节点为最p、q的最近公共祖先
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 好好理解一下这个写法，记住
        if(root == null || root == p || root == q){
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p , q);
        TreeNode right = lowestCommonAncestor(root.right, p , q);
        if(left == null){
            return right;
        }
        if(right == null){
            return left;
        }
        return root;

        // 下面解法时间复杂度高，每次都要去遍历一遍左右子树，不理想
        /*
        if (dfs(root.left, p) && dfs(root.left, q)) {
            return lowestCommonAncestor(root.left, p, q);
        } else if (dfs(root.right, p) && dfs(root.right, q)) {
            return lowestCommonAncestor(root.right, p, q);
        } else {
            return root;
        }
        */
    }

    public boolean dfs(TreeNode node, TreeNode target) {
        if (node == null) {
            return false;
        }
        if (node.val == target.val) {
            return true;
        }
        return dfs(node.left, target) || dfs(node.right, target);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
