package com.example.demo.A_lc;
//给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
//
// 叶子节点 是指没有子节点的节点。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
//输出：[[5,4,11,2],[5,8,4,5]]
// 
//
// 示例 2： 
//
// 
//
// 
//输入：root = [1,2,3], targetSum = 5
//输出：[]
// 
//
// 示例 3： 
//
// 
//输入：root = [1,2], targetSum = 0
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 树中节点总数在范围 [0, 5000] 内 
// -1000 <= Node.val <= 1000 
// -1000 <= targetSum <= 1000 
// 
//
// 注意：本题与主站 113 题相同：https://leetcode-cn.com/problems/path-sum-ii/ 
// Related Topics 树 深度优先搜索 回溯 二叉树 👍 354 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class Solution34 {
    List<List<Integer>> result = new ArrayList<>();
    Stack<Integer> stack = new Stack<>();
    int target;

    public List<List<Integer>> pathSum(TreeNode root, int target) {
        // 深度优先遍历
        this.target = target;
        /*
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        List<Integer> list = new ArrayList<>();
        while(!stack.isEmpty() || node != null){
            if(node != null){
                stack.push(node);
                node = node.left;
            }else{
                node = stack.pop();
                node = node.right;
            }
        }
        */
        dfs(root, 0);
        return result;
    }

    public void dfs(TreeNode node, int sum) {
        if (node == null) {
            return;
        }
        stack.push(node.val);
        sum += node.val;
        // 只有走到叶子节点了，才算路径完成
        if (sum == target && node.left == null && node.right == null) {
            result.add(new ArrayList<>(stack));
        }
        dfs(node.left, sum);
        dfs(node.right, sum);
        // 不要认为到这里就是叶子节点了！
        // 每个节点的左右节点访问完都会走到这里！
        //if (sum == target) {
        //    result.add(new ArrayList<>(stack));
        //}
        stack.pop();// 把访问过的节点pop掉
    }


    public List<List<Integer>> pathSum111(TreeNode root, int target) {
        // 以root为根，找路径，到叶子节点并且和为target
        if (root == null) {
            return null;
        }
        dfs111(root, 0, target, new Stack<>());
        return result;
    }

    public void dfs111(TreeNode node, int currentSum, int target, Stack<Integer> stack) {
        if (node == null) {
            return;
        }
        stack.push(node.val);
        currentSum += node.val;
        if (currentSum == target && node.left == null && node.right == null) {
            result.add(new ArrayList<>(stack));
        }
        dfs111(node.left, currentSum, target, stack);
        dfs111(node.right, currentSum, target, stack);
        // 走到这里说明左右节点被访问过了，路径一定包含该节点了，弹出该节点
        stack.pop();
    }


}
//leetcode submit region end(Prohibit modification and deletion)
