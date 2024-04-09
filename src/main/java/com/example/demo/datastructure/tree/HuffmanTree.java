package com.example.demo.datastructure.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 哈夫曼树
 * <p>
 * 最常用字符，用最少的位数表示，这样可以压缩数据，
 * 但要求每个代码都不能是其它代码的前缀，比如E用01来表示，那么X就不能用01011000表示。
 * </p>
 * <p>
 * HuffmanTree，只有叶子节点才是有效的数据节点，其他的非叶子节点是为了构造出哈夫曼而引入的。（很重要）
 * </p>
 *
 * @author yangjinyu
 * @time 2021/6/14 20:15
 */
public class HuffmanTree<E extends Comparable<? super E>> {
    private TreeNode<E> root;

    public HuffmanTree(List<TreeNode<E>> nodes) {
        root = createTree(nodes);
    }

    public void show() {
        TreeOperation.show(root);
    }

    private TreeNode<E> createTree(List<TreeNode<E>> nodes) {
        while (nodes.size() > 1) {
            // 快速排序
            quickSort(nodes);// 每次都会重新排序保证权值高的在上层
            // 获取权值最小的两个节点
            TreeNode<E> left = nodes.get(0);
            TreeNode<E> right = nodes.get(1);
            // 生成新节点，新节点的权值为两个子节点的权值之和
            TreeNode<E> node = new TreeNode<>(null, left.weight + right.weight);
            node.left = left;
            node.right = right;// 这两步保证了新节点（无效节点）肯定不是叶子节点
            left.parent = node;
            right.parent = node;
            // 删除权值最小的两个节点
            nodes.remove(0);
            nodes.remove(0);
            // 将新节点加入到集合中
            nodes.add(node);
        }
        return nodes.get(0);
    }

    private void quickSort(List<TreeNode<E>> nodes) {
        doQuickSort(nodes, 0, nodes.size() - 1);
    }

    private void doQuickSort(List<TreeNode<E>> nodes, int start, int end) {
        if (start < end) {
            TreeNode<E> pivot = nodes.get(start);
            int i = start;// 使用++i，第一位作为基准
            int j = end + 1;// 使用--j
            while (i <= j) {// 相遇则跳出，所以有等号
                while (i < nodes.size() - 1 && nodes.get(++i).weight < pivot.weight) {
                }
                while (j > 0 && nodes.get(--j).weight > pivot.weight) {
                }
                if (i >= j) {// 相遇则跳出，所以有等号
                    break;
                } else {
                    swap(nodes, i, j);
                }
            }
            swap(nodes, start, j);
            doQuickSort(nodes, start, j - 1);
            doQuickSort(nodes, j + 1, end);
        }
    }

    private void swap(List<TreeNode<E>> nodes, int i, int j) {
        TreeNode<E> temp = nodes.get(i);
        nodes.set(i, nodes.get(j));
        nodes.set(j, temp);
    }

    public static void main(String[] args) {
        List<TreeNode<String>> nodes = new ArrayList<>();
        nodes.add(new TreeNode<>("A", 40.0));
        nodes.add(new TreeNode<>("B", 8.0));
        nodes.add(new TreeNode<>("C", 10.0));
        nodes.add(new TreeNode<>("D", 30.0));
        nodes.add(new TreeNode<>("E", 10.0));
        nodes.add(new TreeNode<>("F", 2.0));
        HuffmanTree<String> huffmanTree = new HuffmanTree<>(nodes);
        huffmanTree.show();
    }
}