package com.example.demo.algorithm.interview;

import lombok.Data;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * 对给定的一个二叉树，输出第一条最长路径
 *                1
 *              |   \
 *            2      3
 *           | \    | \
 *          4   5  6    7
 *         | \      \
 *        8   9      10
 *                  |
 *                 11
 * 【样例输出】
 * 1,3,6,10,11
 */
public class T14 {

    @Data
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.left.left.left = new TreeNode(8);
        root.left.left.right = new TreeNode(9);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        root.right.left.right = new TreeNode(10);
        root.right.left.right.left = new TreeNode(11);

        Solution solution = new Solution();
        solution.dfs(root, new Stack<>());
        System.out.println(Arrays.toString(solution.route.toArray()));
        //LinkedList小知识
        //linkedlist链表当作栈的时候利用push往栈顶添加值，利用peek以及poll获取栈顶以及删除栈顶元素
        //linkedlist链表当做队列的时候利用add以及offer往队尾添加值，利用peek以及poll获取队头的值以及删除队头的值
    }

    static class Solution {
        int maxDepth = 0;
        List<Integer> route = new LinkedList<>();

        public void dfs(TreeNode node, Stack<TreeNode> stack) {
            if(node == null){
                return;
            }
            //先序遍历
            stack.push(node);
            //叶子节点，记录最长路径
            if(node.left == null && node.right == null){
                if(stack.size() > maxDepth){
                    maxDepth = stack.size();
                    route = stack.stream().map(TreeNode::getVal).collect(Collectors.toList());
                }
            }
            dfs(node.left, stack);
            dfs(node.right, stack);
            stack.pop();
        }
    }

    /**
     * 尝试使用非递归方式求解，很复杂，因为使用栈会把路径上的节点提前pop掉，没法记录完整路径
     * 背住上面的解吧
     * 就像非递归方式无法直接完成后续遍历一样
     */
    static class SolutionAttempt {
        public void dfs(TreeNode node){
            Stack<TreeNode> stack = new Stack<>();
            while(!stack.isEmpty() || node != null){
                if(node != null){
                    stack.push(node);
                    node = node.left;
                }else{
                    node = stack.pop();
                    node = node.right;
                }
            }
        }
    }

}
