package com.example.demo.datastructure.tree;

import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static com.example.demo.datastructure.tree.TreeOperation.ANSI_RED;
import static com.example.demo.datastructure.tree.TreeOperation.ANSI_RESET;

/**
 * 二叉搜索树
 *
 * @author yangjinyu
 * @time 2021/6/12 13:43
 */
public class BinarySearchTree<E extends Comparable<? super E>> implements Tree<E> {

    TreeNode<E> root;

    public BinarySearchTree(TreeNode<E> root) {
        this.root = root;
    }

    @Override
    public TreeNode<E> find(@NonNull E value) {
        TreeNode<E> current = root;
        while (current != null) {
            if (value.equals(current.value)) {
                return current;
            }
            if (value.compareTo(current.value) < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return null;
    }

    @Override
    public boolean insert(E value) {
        TreeNode<E> current = root;
        TreeNode<E> node = new TreeNode<>(value);
        node.red = false;//new Random().nextBoolean();
        if (root == null) {
            root = node;
            return true;
        } else {
            TreeNode<E> parent;
            while (true) {
                parent = current;
                if (value.compareTo(current.value) < 0) {
                    current = current.left;
                    if (current == null) {
                        parent.left = node;
                        node.parent = parent;
                        return true;
                    }
                } else {
                    current = current.right;
                    if (current == null) {
                        parent.right = node;
                        node.parent = parent;
                        return true;
                    }
                }
            }
        }
    }

    @Override
    public TreeNode<E> delete(E value) {
        // 找到删除节点
        if (root == null) {
            System.out.println("tree is empty");
            return null;
        }
        TreeNode<E> node = root;
        while (node != null) {
            if (value.compareTo(node.value) == 0) {
                break;
            }
            if (value.compareTo(node.value) < 0) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        if (node == null) {
            System.out.println("no such node");
            return null;
        }
        // 1.删除节点无子节点
        if (node.left == null && node.right == null) {
            if (node.parent != null) {
                if (node.parent.left == node) {
                    node.parent.left = null;
                } else {
                    node.parent.right = null;
                }
                node.parent = null;
            }
            return node;
        }
        // 2.删除节点有一个节点，需要将其父节点原本指向该节点的引用，改为指向该节点的子节点
        else if (node.left == null || node.right == null) {
            if (node.left != null) {
                if (node.parent != null) {
                    if (node.parent.left == node) {
                        node.parent.left = node.left;
                    } else {
                        node.parent.right = node.left;
                    }
                }
                node.left.parent = node.parent;
                node.left = null;
            } else {
                if (node.parent != null) {
                    if (node.parent.left == node) {
                        node.parent.left = node.right;
                    } else {
                        node.parent.right = node.right;
                    }
                }
                node.right.parent = node.parent;
                node.right = null;
            }
            node.parent = null;
            return node;
        }
        // 3.删除节点有两个节点
        else {
            // 找到后续节点（左节点肯定为空）
            TreeNode<E> nextNode = node.right;
            while (nextNode.left != null) {
                nextNode = nextNode.left;
            }
            // 后续节点的右节点为空
            node.value = nextNode.value;//将后继节点的值赋值给要删除的节点，然后把后继节点删除
            if (nextNode.right == null) {
                if (nextNode == nextNode.parent.right) {
                    nextNode.parent.right = null;
                } else {
                    nextNode.parent.left = null;
                }
            }
            // 后续节点的右节点不为空
            else {
                if (nextNode == nextNode.parent.right) {
                    nextNode.parent.right = nextNode.right;
                } else {
                    nextNode.parent.left = nextNode.right;
                }
                nextNode.right.parent = nextNode.parent;
                nextNode.right = null;
            }
            nextNode.parent = null;
            return nextNode;
        }
        // 删除有必要吗？可以不用真正的删除该节点，只需要在Node类中增加一个标识字段isDelete，
        // 当该字段为true时，表示该节点已经删除，实际没有删除。
        // 那么我们在做比如find()等操作的时候，要先判断isDelete字段是否为true。
        // 这样删除的节点并不会改变树的结构。
    }

    public E findMax() {
        TreeNode<E> current = root;
        TreeNode<E> maxNode = current;
        while (current != null) {
            maxNode = current;
            current = current.right;
        }
        return maxNode.value;
    }

    public void show() {
        TreeOperation.show(root);
    }

    public void inOrderTraverse() {
        // 二叉树结点的中序遍历
        TreeNode<E> node = root;
        Stack<TreeNode<E>> stack = new Stack<>();
        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                node = stack.pop();
                System.out.print(node.value + " ");
                node = node.right;
            }
        }
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>(null);
        tree.insert(21);
        tree.insert(6);
        tree.insert(29);
        tree.insert(1);
        tree.insert(10);
        tree.insert(22);
        tree.insert(50);
        tree.insert(8);
        tree.insert(27);
        tree.insert(76);
        // 二叉搜索树的中序遍历，使输出的集合有序
        tree.inOrderTraverse();
        // 查找最大值
        System.out.println("MAX IS " + tree.findMax());
        // 打印二叉树
        tree.show();
        // 删除节点
        List<TreeNode<Integer>> deletedNodeList = new ArrayList<>();
        System.out.println("DELETE 1");
        deletedNodeList.add(tree.delete(1));
        tree.show();
        System.out.println("DELETE 10");
        deletedNodeList.add(tree.delete(10));
        tree.show();
        System.out.println("DELETE 21");
        deletedNodeList.add(tree.delete(21));
        tree.show();
        System.out.println("DELETE 50");
        deletedNodeList.add(tree.delete(50));
        tree.show();
        System.out.println(deletedNodeList);
        System.out.println(ANSI_RESET.length());
        System.out.println(ANSI_RED.length());
    }
}