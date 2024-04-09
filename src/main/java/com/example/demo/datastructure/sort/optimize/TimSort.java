package com.example.demo.datastructure.sort.optimize;

// 优化后的归并排序
// java.util.TimSort
public class TimSort {
    // 我们来进行归并排序的时候，就进行了许多没必要的“分”，
    // 因为有些子序列本来就是有序的了，随而也导致没必要的“治”。TimSort就是为了解决这一缺陷而生。
    // TimSort的思想是，“分”的时候，直接从左往右，划分成各种不同长度的、有序的子序列，
    // 然后对这些子序列进行归并，这样一来，复杂度就大大降低了。
}