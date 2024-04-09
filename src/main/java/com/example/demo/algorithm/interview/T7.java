package com.example.demo.algorithm.interview;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 输出二叉树宽度
 *
 * @author yangjinyu
 * @time 2022/10/19 20:27
 */
public class T7 {
    static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
    }
    public int breadth(TreeNode root){
        int breadth = 0;
        if(root == null){
            return breadth;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            breadth = Math.max(breadth, size);
            for (int i = 0; i < size; i++) {
                root = queue.poll();
                if(root.left != null){
                    queue.offer(root.left);
                }
                if(root.right != null){
                    queue.offer(root.right);
                }
            }
        }
        return breadth;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode();
        root.left = new TreeNode();
        root.right = new TreeNode();
        root.left.left = new TreeNode();
        System.out.println(new T7().breadth(root));
    }
}