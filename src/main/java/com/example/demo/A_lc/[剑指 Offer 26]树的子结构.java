package com.example.demo.A_lc;//输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
//
// B是A的子结构， 即 A中有出现和B相同的结构和节点值。 
//
// 例如: 
//给定的树 A: 
//
// 3 
// / \ 
// 4 5 
// / \ 
// 1 2 
//给定的树 B： 
//
// 4 
// / 
// 1 
//返回 true，因为 B 与 A 的一个子树拥有相同的结构和节点值。 
//
// 示例 1： 
//
// 输入：A = [1,2,3], B = [3,1]
//输出：false
// 
//
// 示例 2： 
//
// 输入：A = [3,4,5,1,2], B = [4,1]
//输出：true 
//
// 限制： 
//
// 0 <= 节点个数 <= 10000 
// Related Topics 树 深度优先搜索 二叉树 👍 590 👎 0


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
    // Solutions倒数第二个
    // 递归+dfs
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        // 边界条件
        if (A == null || B == null) {
            return false;
        }
        // 从根节点判断B是不是A的子结构
        if (isSame(A, B)) {
            return true;
        }
        // 递归判断以A的左、右节点作为根的子树
        return isSubStructure(A.left, B) || isSubStructure(A.right, B);
    }

    public boolean isSame(TreeNode A, TreeNode B) {
        // B为空，说明B已经访问完了，确定是A的子结构
        if (B == null) {
            return true;
        }
        // B的节点不是空，A的节点是空
        // 或者A和B的值不一致，说明不符合要求
        if (A == null || A.val != B.val) {
            return false;
        }
        return isSame(A.left, B.left) && isSame(A.right, B.right);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
