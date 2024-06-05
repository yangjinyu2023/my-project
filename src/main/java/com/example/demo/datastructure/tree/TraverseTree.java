package com.example.demo.datastructure.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

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
    // 从root向下找，返回的是最后一个left和right都不为空的节点，是最近的公共祖先节点
    public <E extends Comparable<? super E>> TreeNode<E> lowestCommonAncestor(TreeNode<E> root, TreeNode<E> node1, TreeNode<E> node2) {
        // 从root向下，如果一个节点，node1、node2分别在它的左右子树上，这个节点是公共祖先
        // 递归处理
        // 递归结束条件，找到了node1或node2，或者到达叶子节点的末端
        if (root == null || root.value.compareTo(node1.value) == 0 || root.value.compareTo(node2.value) == 0) {
            return root;
        }
        TreeNode<E> left = lowestCommonAncestor(root.left, node1, node2);
        TreeNode<E> right = lowestCommonAncestor(root.right, node1, node2);
        // 如果p和q分别在当前节点的两侧，返回当前节点
        if (left != null && right != null) {
            return root;
        }
        if (left != null) {
            return left;// 说明两个都在左子树，继续往下找
        } else {
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
                // 注意这里还是让node为右节点
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

    // 实现二叉树与其线性表表示之间的互相转换（假设二叉树各个节点的值不重复）
    // 注意区分，不是完全二叉树的数组表示法
    static class TreeAndListConversion {
        //能够通过先序遍历结果恢复原来二叉树的，必须是一种在遍历过程中提供了足够信息以区分左子树和右子树的二叉树。典型的，有两种情况：
        //
        //带有空指针信息的任意二叉树：如果先序遍历在遍历过程中记录了空指针信息
        // （例如，使用特殊符号如null或#来表示没有子节点），那么可以从先序遍历的结果中恢复原来的二叉树，
        // 无论它是满的、完全的还是不完全的。这是因为空指针信息提供了足够的信息来区分何时一个节点的子节点结束，从而可以准确地恢复树的结构。
        //
        //特殊结构的二叉树：对于特定类型的二叉树，即使不记录空指针信息，也可以从先序遍历中恢复原树。
        // 一个例子是满二叉树（Full Binary Tree），在这种树中，每个节点要么没有子节点，要么有两个子节点。
        // 由于这种特殊的结构，我们可以仅通过节点的数量和遍历的顺序来确定树的结构。
        public List<Integer> treeToList(TreeNode<Integer> root) {
            if (root == null) {
                return null;
            }
            List<Integer> list = new ArrayList<>();
            serialize(root, list);
            return list;
        }

        public void serialize(TreeNode<Integer> node, List<Integer> list) {
            if (node == null) {
                list.add(null);
                return;
            }
            list.add(node.value);
            serialize(node.left, list);
            serialize(node.right, list);
        }

        // 将列表转换回二叉树（反序列化）
        public TreeNode<Integer> listToTree(List<Integer> list) {
            if (list == null || list.isEmpty()) {
                return null;
            }
            return deserialize(new ArrayList<>(list)); // 创建列表的副本以避免修改原始列表
        }

        // 递归反序列化树
        private TreeNode<Integer> deserialize(List<Integer> list) {
            if (list.get(0) == null) {
                list.remove(0); // 移除已处理的null元素
                return null;
            }
            // 构建为TreeNode的需要移除掉，这个容易忘记
            TreeNode<Integer> root = new TreeNode<>(list.remove(0)); // 移除并返回列表的第一个元素
            root.left = deserialize(list);
            root.right = deserialize(list);
            return root;
        }

        //要理解并记住这个实现，我们可以将其分解为几个关键概念和步骤：
        //
        //### 关键概念
        //
        //1. **序列化（`treeToList`）**：这是将二叉树转换成线性表（列表）的过程。想象你正在将树的结构“平铺”成一行，以便可以在没有树形结构的情况下存储或传输。
        //
        //2. **反序列化（`listToTree`）**：这是将线性表（列表）转换回二叉树的过程。想象你正在根据一行中的指令重新构建树，恢复其原始结构。
        //
        //### 关键步骤
        //
        //1. **序列化步骤（先序遍历）**：
        //   - **访问根节点**：将当前节点的值加入列表。
        //   - **递归左子树**：然后对左子树执行相同操作。
        //   - **递归右子树**：最后对右子树执行相同操作。
        //   - **处理空节点**：遇到空节点时，添加一个特殊值（如`null`）到列表中以保留树的完整结构信息。
        //
        //2. **反序列化步骤**：
        //   - **读取当前元素**：从列表中取出当前元素（并从列表中移除该元素）作为当前节点的值。
        //   - **构建节点**：如果当前元素是特殊值（如`null`），则当前树节点为`null`；否则，创建一个新的树节点。
        //   - **递归构建子树**：首先构建左子树，然后构建右子树。这一步骤递归地应用于列表中的剩余元素。
        //
        //### 记忆技巧
        //
        //- **序列化 = “平铺”树**：想象你在将树的每个节点按照先序遍历的顺序拿下来，然后按顺序排成一行，空位用`null`填充。
        //- **反序列化 = “重建”树**：想象你拿到了一串珍珠（列表中的元素），按照先序遍历的顺序，一颗颗珍珠（节点）串起来重建树。遇到`null`珍珠就跳过，表示这里没有子节点。
        //
        //通过这样的方式，你可以将序列化想象为将树“拆解”成列表，而反序列化则是根据这个列表“组装”回原来的树。记住这个基本流程和关键步骤，可以帮助你更好地理解和记忆这个实现。
    }
}