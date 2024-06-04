package com.example.demo.A_lc;
//输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。
//如果是则返回 true，否则返回 false。假设输入的数组的任意两个数字都互不相同。
//
// 
//
// 参考以下这颗二叉搜索树： 
//
//      5
//    / \
//   2   6
//  / \
// 1   3 
//
// 示例 1： 
//
// 输入: [1,6,3,2,5]
//输出: false 
//
// 示例 2： 
//
// 输入: [1,3,2,6,5]
//输出: true 
//
// 
//
// 提示： 
//
// 
// 数组长度 <= 1000 
// 
// Related Topics 栈 树 二叉搜索树 递归 二叉树 单调栈 👍 556 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution33 {
    // 很重要的一点：后续遍历（左右根）数组中，根节点为最后一个，
    // 往前找到第一个小于根的，那么它（包含）之前的为左子树，之后的为右子树
    public boolean verifyPostorder(int[] postorder) {
        // 二叉搜索树，左节点<根节点<右节点
        // 根节点为后序遍历中的最后一个
        // 递归判断每颗子树，满足左节点<根节点<右节点，说明该树为二叉搜索树
        return isBinarySearchTree(postorder, 0, postorder.length - 1);
    }

    public boolean isBinarySearchTree(int[] postorder, int s, int e) {
        if (s >= e) {
            return true;
        }
        int root = postorder[e];
        int m;
        // postorder，从根（最后一个）往前找到第一个小于根的节点m
        // 那么[s,m]为左子树，[m+1,e-1]为右子树
        for (m = e - 1; m >= s; m--) {
            if (postorder[m] < root) {
                break;
            }
        }
        // 判断左子树是否都小于root
        for (int i = s; i <= m; i++) {
            if (postorder[i] > root) {
                return false;
            }
        }
        return isBinarySearchTree(postorder, s, m) &&
                isBinarySearchTree(postorder, m + 1, e - 1);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
