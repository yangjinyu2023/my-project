package com.example.demo.A_lc;//请实现两个函数，分别用来序列化和反序列化二叉树。
//
// 你需要设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字
//符串反序列化为原始的树结构。 
//
// 提示：输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方
//法解决这个问题。 
//
// 
//
// 示例： 
//
// 
//输入：root = [1,2,3,null,null,4,5]
//输出：[1,2,3,null,null,4,5]
// 
//
// 
//
// 注意：本题与主站 297 题相同：https://leetcode-cn.com/problems/serialize-and-deserialize-
//binary-tree/ 
// Related Topics 树 深度优先搜索 广度优先搜索 设计 字符串 二叉树 👍 312 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
class Solution37 {

    // Encodes a tree to a single string.
    // 考虑复杂了，
    // 不需要通过先序+中序来重建树，通过数组就能保存树的结构
    // 完全二叉树在数组中的位置（BFS）：根n，左子节点2n+1，右子节点2n+2
    // 但是要构建完全二叉树，比较费劲

    // 替代方案，叶子节点必须是null，
    // 设m 为列表区间 [0,n] 中的 null 节点个数，
    // 那么根n，左子节点2(n-m) + 1，右子节点2(n-m) + 2
    public String serialize(TreeNode root) {
        // 先序:中序
        Stack<TreeNode> stack = new Stack<>();
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        TreeNode node = root;
        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                stack.push(node);
                // 由于没说节点的val不会重复，所以需要hashcode
                list1.add(node.hashCode() + "-" + String.valueOf(node.val));
                node = node.left;
            } else {
                node = stack.pop();
                list2.add(node.hashCode() + "-" + String.valueOf(node.val));
                node = node.right;
            }
        }
        String data = "";
        if (!list1.isEmpty() && !list2.isEmpty()) {
            data = data.concat(String.join(",", list1));
            data = data.concat(":");
            data = data.concat(String.join(",", list2));
        }
        return data;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || "".equals(data)) {
            return null;
        }
        String[] arr = data.split(":");
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
        int val = Integer.parseInt(preOrder[preStart].substring(preOrder[preStart].indexOf("-") + 1));
        TreeNode root = new TreeNode(val);
        int index = inStart, leftTreeLength;
        for (; index <= inEnd; index++) {
            if (inOrder[index].equals(preOrder[preStart])) {
                break;
            }
        }
        // 通过中序，可以得知左子树长度
        leftTreeLength = index - inStart;
        root.left = build(preOrder, preStart + 1, preStart + leftTreeLength, inOrder, inStart, index - 1);
        root.right = build(preOrder, preStart + leftTreeLength + 1, preEnd, inOrder, index + 1, inEnd);
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
//leetcode submit region end(Prohibit modification and deletion)
