package com.example.demo.algorithm.interview;

import java.util.Arrays;

/**
 * 给定两个有序数组，求并集，要求并集有序
 *
 * @author yangjinyu
 * @time 2022/9/3 16:06
 */
public class T12 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new T12().merge(new int[] { 1, 3, 5 }, new int[] { 1, 2, 6, 7 })));
        System.out.println(Arrays.toString(new T12().merge_new(new int[] { 1, 3, 5 }, new int[] { 1, 2, 6, 7 })));
    }

    // 就是归并排序的merge过程
    public int[] merge(int[] arr1, int[] arr2) {
        if (arr1 == null || arr1.length == 0) {
            return arr2;
        }
        if (arr2 == null || arr2.length == 0) {
            return arr1;
        }
        int[] tmp = new int[arr1.length + arr2.length];
        int i = 0, j = 0, t = 0;
        while (i <= arr1.length - 1 && j <= arr2.length - 1) {
            if (arr1[i] <= arr2[j]) {
                tmp[t++] = arr1[i++];
            }
            else {
                tmp[t++] = arr2[j++];
            }
        }
        while (i <= arr1.length - 1) {
            tmp[t++] = arr1[i++];
        }
        while (j <= arr2.length - 1) {
            tmp[t++] = arr2[j++];
        }
        return tmp;
    }

    public int[] merge_new(int[] arr1, int[] arr2) {
        int l1 = arr1.length;
        int l2 = arr2.length;
        int[] res = new int[l1 + l2];
        int i = 0, j = 0, r = 0;
        while (i < l1 && j < l2) {
            res[r++] = arr1[i] <= arr2[j] ? arr1[i++] : arr2[j++];
        }
        while (i < l1) {
            res[r++] = arr1[i++];
        }
        while (j < l2) {
            res[r++] = arr2[j++];
        }
        return res;
    }
}