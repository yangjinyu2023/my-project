package com.example.demo.A_lc;
//输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
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
        // 如果A和B都不是null并且val相等，继续往下判断
        return isSame(A.left, B.left) && isSame(A.right, B.right);
    }

    //使用先序遍历的结果来判断B是否是A的子结构，在某些情况下可能行不通。
    // 原因在于先序遍历结果是一种线性结构，它丢失了原树中部分结构信息，尤其是节点之间的层级关系。
    // 因此，仅凭先序遍历的结果可能无法准确判断一个树是否为另一个树的子结构。
    //
    //考虑以下两个因素：
    //
    //节点值的重复：如果树中包含重复的值，仅凭先序遍历结果可能无法区分不同的节点。
    // 例如，树A中可能有多个节点值为4，它们的子树结构完全不同。
    //
    //子树的层级信息丢失：先序遍历结果无法反映节点之间的层级关系。
    // 例如，即使B的先序遍历结果（4、1）是A的先序遍历结果（3、4、1、5、2）的子序列，
    // 也无法确保B是A的子结构，因为这种子序列可能跨越了多个层级。
}
//leetcode submit region end(Prohibit modification and deletion)
