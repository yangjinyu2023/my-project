package com.example.demo.solutions;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Codec {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        //先序|中序
        Stack<TreeNode> stack = new Stack<>();
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        TreeNode node = root;
        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                stack.push(node);
                list1.add(node.hashCode() + String.valueOf(node.val));
                node = node.left;
            } else {
                node = stack.pop();
                list2.add(String.valueOf(node.val));
                node = node.right;
            }
        }
        String data = "";
        if (!list1.isEmpty() && !list2.isEmpty()) {
            data = data.concat(String.join(",",list1));
            data = data.concat("|");
            data = data.concat(String.join(",", list2));
        }
        return data;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || "".equals(data)) {
            return null;
        }
        String[] arr = data.split("\\|");
        String[] preOrder = arr[0].split(",");
        String[] inOrder = arr[1].split(",");
        // 根据前序和中序，重建二叉树（和剑指offer7逻辑一致）
        return build(preOrder, 0, preOrder.length - 1, inOrder, 0, inOrder.length - 1);
    }

    public TreeNode build(String[] preOrder, int preStart, int preEnd, String[] inOrder, int inStart, int inEnd) {
        // 只有大于的情况，才不构建节点；等于时需要构建一个节点，稍微举个例子就明白了
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }
        int val = Integer.parseInt(preOrder[preStart].substring(preOrder[preStart].indexOf("-")));
        TreeNode root = new TreeNode(val);
        int index = inStart, leftTreeLength;
        for (; index <= inEnd; index++) {
            if (inOrder[index].equals(preOrder[preStart])) {
                break;
            }
        }
        leftTreeLength = index - inStart;
        root.left = build(preOrder, preStart + 1, preStart + leftTreeLength, inOrder, inStart, index - 1);
        root.right = build(preOrder, preStart + leftTreeLength + 1, preEnd, inOrder, index + 1, inEnd);
        return root;
    }
}