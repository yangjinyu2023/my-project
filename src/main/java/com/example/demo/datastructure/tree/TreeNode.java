package com.example.demo.datastructure.tree;

/**
 * 树节点
 *
 * @author yangjinyu
 * @time 2021/6/12 13:45
 */
public class TreeNode<E> {
    boolean red;// 红黑树使用
    double weight;// 哈夫曼树使用
    TreeNode<E> left;
    TreeNode<E> right;
    TreeNode<E> parent;
    E value;

    public TreeNode(E value) {
        this.value = value;
    }

    public TreeNode(E value, double weight) {
        this.weight = weight;
        this.value = value;
    }

    public TreeNode(E value, boolean red) {
        this.red = red;
        this.value = value;
    }
}