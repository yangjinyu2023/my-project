package com.example.demo.A_lc;//输入某二叉树的前序遍历和中序遍历的结果，请构建该二叉树并返回其根节点。
//
// 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。 
//
// 
//
// 示例 1: 
//
// 
//Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
//Output: [3,9,20,null,null,15,7]
// 
//
// 示例 2: 
//
// 
//Input: preorder = [-1], inorder = [-1]
//Output: [-1]
// 
//
// 
//
// 限制： 
//
// 0 <= 节点个数 <= 5000 
//
// 
//
// 注意：本题与主站 105 题重复：https://leetcode-cn.com/problems/construct-binary-tree-from-
//preorder-and-inorder-traversal/ 
// Related Topics 树 数组 哈希表 分治 二叉树 👍 824 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
class Solution7 {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // 通过前序遍历能确定root的位置
        // 通过root的位置能确定左子树和右子树
        // 通过这种方式，对左右子树进行递归判断，得到完整的树结构
        return build(preorder, 0, preorder.length - 1, inorder, 0, inorder.length -1);
    }
    TreeNode build(int[] preorder, int preS, int preE, int[] inorder, int inS, int inE){
        // 递归结束条件
        if(preS > preE || inS > inE){
            return null;
        }
        // 通过前序遍历数组找到root
        int val = preorder[preS];
        TreeNode root = new TreeNode(val);
        // 通过root在中序遍历数组中的位置确定左子树长度
        int index = 0;
        for (index = inS; index <= inE; index++) {
            if(inorder[index] == val){
                break;
            }
        }
        int leftSize = index - inS;
        // Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
        // 递归左子树，
        // 前序左子树[prsS+1, preS+leftSize]，前序根节点永远在第一位
        // 中序左子树[ins, index-1]，中序需要根据根节点位置index确定子树
        root.left  = build(preorder, preS + 1, preS + leftSize, inorder, inS, index - 1);
        // 递归右子树
        // 前序右子树[preS+leftSize+1, preE]，前序根节点永远在第一位
        // 中序右子树[index+1, inE]，中序需要根据根节点位置index确定子树
        root.right = build(preorder, preS + leftSize + 1, preE, inorder, index + 1, inE);
        return root;
    }


    public TreeNode buildTree111(int[] preorder, int[] inorder){
        // 思路是，递归，每次传入子树的先序和中序
       return build111(preorder, 0, preorder.length - 1, inorder, 0, inorder.length -1);
    }
    private TreeNode build111(int[] preorder, int ps, int pe, int[] inorder, int is, int ie) {
        // 结束递归
        if(ps > pe || is > ie){
            return null;
        }
        int rootVal = preorder[ps];
        TreeNode root = new TreeNode(rootVal);
        // 确定在中序中的位置
        int i = is;
        for (; i <= ie; i++) {
            if(inorder[i] == rootVal){
                break;
            }
        }
        int leftLength = i - is;

        root.left = build111(preorder, ps + 1, ps + leftLength, inorder, is, i-1);
        root.right = build111(preorder, ps + leftLength + 1, pe, inorder, i+1, ie);
        return root;
    }

}
//leetcode submit region end(Prohibit modification and deletion)
