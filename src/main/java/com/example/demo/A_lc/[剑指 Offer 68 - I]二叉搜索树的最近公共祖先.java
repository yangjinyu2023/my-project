package com.example.demo.A_lc; /**
 * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
 * <p>
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个
 * 节点也可以是它自己的祖先）。”
 * <p>
 * 例如，给定如下二叉搜索树: root = [6,2,8,0,4,7,9,null,null,3,5]
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
 * 输出: 6
 * 解释: 节点 2 和节点 8 的最近公共祖先是 6。
 * <p>
 * <p>
 * 示例 2:
 * <p>
 * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
 * 输出: 2
 * 解释: 节点 2 和节点 4 的最近公共祖先是 2, 因为根据定义最近公共祖先节点可以为节点本身。
 * <p>
 * <p>
 * <p>
 * 说明:
 * <p>
 * <p>
 * 所有节点的值都是唯一的。
 * p、q 为不同节点且均存在于给定的二叉搜索树中。
 * <p>
 * <p>
 * 注意：本题与主站 235 题相同：https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-
 * binary-search-tree/
 * <p>
 * Related Topics 树 深度优先搜索 二叉搜索树 二叉树 👍 249 👎 0
 */

//leetcode submit region begin(Prohibit modification and deletion)

import java.util.LinkedList;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode(int x) { val = x; }
 * }
 */
class Solution68_1 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 求公共祖先问题，记住！
        // 从root向下开始找，如果p、q分别在某个节点的左右子树，那么该节点就是p、q的公共祖先
        TreeNode node = root;
        while (node != null){
            if(p.val < node.val && q.val < node.val){
                 node = node.left;
            }else if (p.val > node.val && q.val > node.val){
                node = node.right;
            }else{
                break;
            }
        }
        return node;

        // 虽然求解正确，但是写法复杂，且空间复杂度为0(2N)
        /*
        // 找他们的parent，直到两个parent相等，即为公共祖先
        // 将p和q的父类有顺序的放在队列，方便比较
        if (p == root || q == root) {
            return root;
        }
        TreeNode pNode = root, qNode = root;
        Queue<TreeNode> pQueue = new LinkedList<>();
        Queue<TreeNode> qQueue = new LinkedList<>();
        while (pNode != null || qNode != null) {
            if (pNode != null) {
                pQueue.offer(pNode);
                if (p.val == pNode.val) {
                    pNode = null;
                } else {
                    if (p.val > pNode.val) {
                        pNode = pNode.right;
                    } else {
                        pNode = pNode.left;
                    }
                }
            }
            if (qNode != null) {
                qQueue.offer(qNode);
                if (q.val == qNode.val) {
                    qNode = null;
                } else {
                    if (q.val > qNode.val) {
                        qNode = qNode.right;
                    } else {
                        qNode = qNode.left;
                    }
                }
            }
        }
        TreeNode commonAncestor = null;
        while (!pQueue.isEmpty() || !qQueue.isEmpty()) {
           if(pQueue.peek() == qQueue.peek()){
               commonAncestor = pQueue.poll();
               qQueue.poll();
           }else{
               break;
           }
        }
        return commonAncestor;
        */
    }
}
//leetcode submit region end(Prohibit modification and deletion)
