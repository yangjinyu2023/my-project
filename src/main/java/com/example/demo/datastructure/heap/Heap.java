package com.example.demo.datastructure.heap;

/**
 * 堆，（是一个完全二叉树，可以用数组存储）
 * 堆是弱序的（只保证根节点大于（或小于）其子节点），所以想要遍历堆是很困难的，基本上堆是不支持遍历的
 * <p>
 * 对于堆来说，能够快速的移除最大（或最小）节点，也就是根节点，以及能快速插入新的节点，这两个操作就足够了。
 * 堆是完全二叉树，除了树的最后一层节点不需要是满的，其它的每一层从左到右都是满的。
 * 通常用数组实现：设节点的索引值为index，那么：节点的左子节点是 2*index+1，节点的右子节点是 2*index+2，节点的父节点是 （index-1）/2。
 * </p>
 * @author yangjinyu
 * @time 2021/6/21 11:46
 */
public class Heap<E extends Comparable<? super E>> {
    // 左孩子：2index+1 右孩子：2index+2 父节点：（index-1）/2
    private Object[] node;
    private int capacity;
    private int size;

    public Heap(int capacity) {
        this.capacity = capacity;
        this.node = new Object[capacity];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    // 从最后一个元素插入
    public void insert(E e) {
        if (isFull()) {
            System.out.println("heap is full");
            return;
        }
        if (isEmpty()) {
            node[0] = e;
        }
        int currentIndex = size;
        node[currentIndex] = e;
        while (currentIndex != 0) {
            int parentIndex = (currentIndex - 1) / 2;
            @SuppressWarnings("unchecked") E parent = (E) node[parentIndex];
            if (e.compareTo(parent) > 0) {
                node[currentIndex] = parent;
                node[parentIndex] = e;
                currentIndex = parentIndex;
            } else {
                break;
            }
        }
        size++;
    }

    /**
     * 移除第一个元素（根节点）
     *
     * <p>
     * 1、移走根
     * <p>
     * 2、把最后一个节点移动到根的位置
     * <p>
     * 3、一直向下筛选这个节点，直到它在一个大于它的节点之下，小于它的节点之上为止。
     */
    @SuppressWarnings("unchecked")
    public E remove() {
        if (isEmpty()) {
            System.out.println("heap is empty");
            return null;
        }
        // 移走根，把最后一个节点移动到根的位置
        Object root = node[0];
        node[0] = node[size - 1];
        node[--size] = null;
        int index = 0;
        // 一直向下筛选这个节点，直到它在一个大于它的节点之下，小于它的节点之上为止
        while (2 * index + 1 < size) {// 至少存在一个子节点
            E e = (E) node[index];
            E left = (E) node[2 * index + 1];
            E right = (E) node[2 * index + 2];
            if (right != null) {// 则left一定不为null，e一定小于两个子节点其中一个
                // 取两者中的较大者作为新的parent
                if (left.compareTo(right) > 0) {
                    node[index] = left;
                    index = 2 * index + 1;
                } else {
                    node[index] = right;
                    index = 2 * index + 2;
                }
            } else if (left.compareTo(e) > 0) {// 此时left不为null，right为null，需要判断子节点和e的大小
                node[index] = left;
                index = 2 * index + 1;
            } else {
                break;
            }
            node[index] = e;
        }
        return (E) root;
    }

    public static void main(String[] args) {
        Heap<Integer> heap = new Heap<>(20);
        heap.insert(70);
        heap.insert(10);
        heap.insert(80);
        heap.insert(-20);
        heap.insert(60);
        heap.insert(90);
        heap.insert(0);
        heap.insert(30);
        heap.insert(100);
        System.out.println(heap.remove());
        System.out.println(heap.remove());
        System.out.println(heap.remove());
        System.out.println(heap.remove());
        System.out.println(heap.remove());
        System.out.println(heap.remove());
        System.out.println(heap.remove());
        System.out.println(heap.remove());
    }
}