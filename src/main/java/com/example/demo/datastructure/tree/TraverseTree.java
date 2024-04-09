package com.example.demo.datastructure.tree;

import java.util.*;

/**
 * 二叉树的遍历 https://www.cnblogs.com/mq0036/p/14542765.html
 *
 * @author yangjinyu
 * @time 2021/6/8 17:52
 */
public class TraverseTree {
    /**
     * 前序遍历（递归方式）
     */
    public static <E extends Comparable<? super E>> void preOrderTraverse_recursion(TreeNode<E> node) {
        if (node != null) {
            System.out.print(node.value);
            preOrderTraverse_recursion(node.left);
            preOrderTraverse_recursion(node.right);
        }
    }

    /**
     * 前序遍历（非递归方式）
     */
    public static <E extends Comparable<? super E>> void preOrderTraverse(TreeNode<E> node) {
        Stack<TreeNode<E>> stack = new Stack<>();
        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                System.out.print(node.value);
                stack.push(node);
                node = node.left;
            } else {
                node = stack.pop();
                node = node.right;
            }
        }
    }

    /**
     * 中序遍历（递归方式）
     */
    public static <E extends Comparable<? super E>> void inOrderTraverse_recursion(TreeNode<E> node) {
        if (node != null) {
            inOrderTraverse_recursion(node.left);
            System.out.print(node.value);
            inOrderTraverse_recursion(node.right);
        }
    }

    /**
     * 中序遍历（非递归方式）
     */
    public static <E extends Comparable<? super E>> void inOrderTraverse(TreeNode<E> node) {
        Stack<TreeNode<E>> stack = new Stack<>();
        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                node = stack.pop();
                System.out.print(node.value);
                node = node.right;
            }
        }
    }

    /**
     * 后序遍历（递归方式）
     */
    public static <E extends Comparable<? super E>> void postOrderTraverse_recursion(TreeNode<E> node) {
        if (node != null) {
            postOrderTraverse_recursion(node.left);
            postOrderTraverse_recursion(node.right);
            System.out.print(node.value);
        }
    }

    /**
     * 后序遍历（非递归方式）
     */
    public static <E extends Comparable<? super E>> void postOrderTraverse(TreeNode<E> node) {
        // 前序：根节点->左子树->右子树
        // 后序：左子树->右子树->根节点
        // 改造前序算法：根节点->右子树->左子树
        // 逆序：左子树->右子树->根节点
        Stack<TreeNode<E>> stack = new Stack<>();
        List<E> list = new ArrayList<>();
        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                stack.push(node);
                list.add(node.value);
                node = node.right;
            } else {
                node = stack.pop();
                node = node.left;
            }
        }
        Collections.reverse(list);
        list.forEach(System.out::print);
    }

    /**
     * 广度优先遍历（层次遍历），深度优先遍历即前序遍历
     * </b>
     * 对于完全二叉树的BFS，根n，左子节点2n+1，右子节点2n+2
     */
    public static <E extends Comparable<? super E>> void levelTraverse(TreeNode<E> root) {
        Queue<TreeNode<E>> queue = new LinkedList<>();
        TreeNode<E> node = root;
        queue.offer(node);
        while (!queue.isEmpty()) {
            node = queue.poll();
            System.out.print(node.value);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }

    // 计算树的深度，DFS。这里千万别去用栈辅助那种深度优先！仔细想下，务必记住
    public <E extends Comparable<? super E>> int maxDepth_DFS(TreeNode<E> root) {
        // DFS，树的最大深度，为左子树的最大深度和右子树的最大深度中的较大值加1
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth_DFS(root.left), maxDepth_DFS(root.right)) + 1;
    }

    // 计算树的深度，BFS
    public <E extends Comparable<? super E>> int maxDepth2(TreeNode<E> root) {
        // BFS，遍历完每层，深度加1
        if (root == null) {
            return 0;
        }
        int level = 0;
        Queue<TreeNode<E>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            for (int i = queue.size(); i > 0; i--) {// 关键点在这里，剑指offer32题也是。注意queue.size()一直在变！
                TreeNode<E> node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            level++;
        }
        return level;
    }

    // 计算二叉树两个节点p、q的最近公共祖先（二叉树中无重复值）
    public <E extends Comparable<? super E>> TreeNode<E> lowestCommonAncestor(TreeNode<E> root, TreeNode<E> node1, TreeNode<E> node2) {
        // 从root向下，如果一个节点，node1、node2分别在它的左右子树上，这个节点是公共祖先
        // 递归处理
        // 递归结束条件，找到了node1或node2，或都没找到
        if(root == null || root.value.compareTo(node1.value) == 0 || root.value.compareTo(node2.value) == 0){
            return root;
        }
        TreeNode<E> left = lowestCommonAncestor(root.left, node1, node2);
        TreeNode<E> right = lowestCommonAncestor(root.right, node1, node2);
        if(left != null && right != null){
            return root;
        }else if(left != null){
            return left;// 说明两个都在左子树，继续往下找
        }
        else{
            return right;// 说明两个都在右子树，继续往下找
        }
    }

    // 树的镜像
    //      4
    //
    //   /   \
    //
    //  2     7
    //
    // / \   / \
    //
    //1   3 6   9
    //镜像输出：
    //
    //     4
    //
    //   /   \
    //
    //  7     2
    //
    // / \   / \
    //
    //9   6 3   1
    public static TreeNode<Integer> mirrorTree() {
        TreeNode<Integer> root = new TreeNode<>(4);
        TreeNode<Integer> l = new TreeNode<>(2);
        TreeNode<Integer> r = new TreeNode<>(7);
        TreeNode<Integer> ll = new TreeNode<>(1);
        TreeNode<Integer> lr = new TreeNode<>(3);
        TreeNode<Integer> rl = new TreeNode<>(6);
        TreeNode<Integer> rr = new TreeNode<>(9);
        root.left = l;
        root.right = r;
        l.left = ll;
        l.right = lr;
        r.left = rl;
        r.right = rr;
        Stack<TreeNode<Integer>> stack = new Stack<>();
        TreeNode<Integer> node = root;
        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                node = stack.pop();
                // 左右互换
                TreeNode<Integer> right = node.right;
                node.right = node.left;
                node.left = right;
                node = right;
            }
        }
        return root;
    }

    public TreeNode<Integer> mirrorTree(TreeNode<Integer> root) {
        // 更简单的写法，后续遍历，先遍历左右子节点，遍历到根节点时把左右子节点互换
        if (root == null) {
            return null;
        }
        TreeNode<Integer> left = mirrorTree(root.left);
        TreeNode<Integer> right = mirrorTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

    public static void main(String[] args) {
        mirrorTree();
        TreeNode<String> root = createTree();
        TreeOperation.show(root);
        // 广度优先遍历 ++*a*+gbc*fde
        System.out.println("广度优先遍历");
        levelTraverse(root);
        // 深度优先遍历
        // 前序遍历（递归方式） ++a*bc*+*defg
        System.out.println();
        System.out.println("前序遍历（递归方式）");
        preOrderTraverse_recursion(root);
        // 前序遍历（非递归方式） ++a*bc*+*defg
        System.out.println();
        System.out.println("前序遍历（非递归方式）");
        preOrderTraverse(root);
        // 中序遍历（递归方式） a+b*c+d*e+f*g
        System.out.println();
        System.out.println("中序遍历（递归方式）");
        inOrderTraverse_recursion(root);
        // 中序遍历（非递归方式） a+b*c+d*e+f*g
        System.out.println();
        System.out.println("中序遍历（非递归方式）");
        inOrderTraverse(root);
        // 后序遍历（递归方式） abc*+de*f+g*+
        System.out.println();
        System.out.println("后序遍历（递归方式）");
        postOrderTraverse_recursion(root);
        // 后序遍历（非递归方式） abc*+de*f+g*+
        System.out.println();
        System.out.println("后序遍历（非递归方式）");
        postOrderTraverse(root);
    }

    /**
     * 中缀表达式 a+b*c+(d*e+f)*g
     */
    public static TreeNode<String> createTree() {
        TreeNode<String> root = new TreeNode<>("+");
        TreeNode<String> l = new TreeNode<>("+");
        TreeNode<String> ll = new TreeNode<>("a");
        TreeNode<String> lr = new TreeNode<>("*");
        TreeNode<String> lrl = new TreeNode<>("b");
        TreeNode<String> lrr = new TreeNode<>("c");
        TreeNode<String> r = new TreeNode<>("*");
        TreeNode<String> rl = new TreeNode<>("+");
        TreeNode<String> rr = new TreeNode<>("g");
        TreeNode<String> rll = new TreeNode<>("*");
        TreeNode<String> rlr = new TreeNode<>("f");
        TreeNode<String> rlll = new TreeNode<>("d");
        TreeNode<String> rllr = new TreeNode<>("e");
        //root
        root.parent = null;
        root.left = l;
        root.right = r;
        //left tree
        l.parent = root;
        l.left = ll;
        l.right = lr;
        ll.parent = l;
        ll.left = null;
        ll.right = null;
        lr.parent = l;
        lr.left = lrl;
        lr.right = lrr;
        lrl.parent = lr;
        lrl.left = null;
        lrl.right = null;
        lrr.parent = lr;
        lrr.left = null;
        lrr.right = null;
        //right tree
        r.parent = root;
        r.left = rl;
        r.right = rr;
        rl.parent = r;
        rl.left = rll;
        rl.right = rlr;
        rr.parent = r;
        rr.left = null;
        rr.right = null;
        rll.parent = rl;
        rll.left = rlll;
        rll.right = rllr;
        rlr.parent = rl;
        rlr.left = null;
        rlr.right = null;
        rlll.parent = rll;
        rlll.left = null;
        rlll.right = null;
        rllr.parent = rll;
        rllr.left = null;
        rllr.right = null;
        return root;
    }
}