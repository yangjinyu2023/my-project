package com.example.demo.A_lc;
//从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行。
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
//  [9,20],
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
//
// 注意：本题与主站 102 题相同：https://leetcode-cn.com/problems/binary-tree-level-order-
//traversal/ 
// Related Topics 树 广度优先搜索 二叉树 👍 235 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.*;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
class Solution33II {
    public List<List<Integer>> levelOrder(TreeNode root) {
        // 广度优先遍历，每一层打印一行，最好是TreeNode中加个level属性
        // 但是不能改变提供的数据结构，这里设计了一个levelMap，还是麻烦了一些
        /*
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Map<TreeNode, Integer> levelMap = new HashMap<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        levelMap.put(root, 0);
        result.add(Arrays.asList(root.val));
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            int level = levelMap.get(node);
            if (node.left != null) {
                int currentLevel = level + 1;
                queue.offer(node.left);
                levelMap.put(node.left, currentLevel);
                if (currentLevel >= result.size()) {
                    List<Integer> list = new ArrayList<>();
                    list.add(node.left.val);
                    result.add(list);
                } else {
                    result.get(currentLevel).add(node.left.val);
                }
            }
            if (node.right != null) {
                int currentLevel = level + 1;
                queue.offer(node.right);
                levelMap.put(node.right, currentLevel);
                if (currentLevel >= result.size()) {
                    List<Integer> list = new ArrayList<>();
                    list.add(node.right.val);
                    result.add(list);
                } else {
                    result.get(currentLevel).add(node.right.val);
                }
            }
        }
        return result;
        */
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) {
            queue.offer(root);
        }
        while (!queue.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            // 每次将一层出队，下一层会全部进队
            // 这里queue是在变化的，所以不能(int i = 0; i > queue.size(); i++)
            for (int i = queue.size(); i > 0; i--) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            result.add(list);
        }
        return result;
    }

    public List<List<Integer>> levelOrder111(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            List<Integer> list = new ArrayList<>();
            // queue.size在for循环内会变
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if(node.left != null){
                    queue.add(node.left);
                }
                if(node.right != null){
                    queue.add(node.right);
                }
            }
            result.add(list);
        }
        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
