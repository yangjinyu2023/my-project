package com.example.demo.datastructure.tree;

public interface Tree<E> {
    TreeNode<E> find(E value);
    boolean insert(E value);
    TreeNode<E> delete(E value);
}
