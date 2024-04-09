package com.example.demo.datastructure.sort;

/**
 * 选择排序
 *
 * @author yangjinyu
 * @time 2021/6/7 16:34
 */
public class ChoiceSort {
    /**
     * <p>
     * 选择排序和冒泡排序执行了相同次数的比较：n（n-1）/2，但是至多只进行了n次交换。
     * </p>
     * <p>
     * 时间复杂度O(N^2) 。但是由于选择排序交换的次数少，所以选择排序比冒泡排序快。
     * </p>
     */
    private static void sort(int[] array) {
        if (array == null) {
            throw new RuntimeException("array can not be null");
        }
        // i<length -1是因为j=i+1
        for (int i = 0; i < array.length - 1; i++) {
            int min = i;// 这步是关键，把最小的index记录下来！然后往后找，让min是最小的值的索引
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[min]) {
                    min = j;
                }
            }
            if (min != i) {
                // swap
                int temp = array[i];
                array[i] = array[min];
                array[min] = temp;
            }
        }
    }

    public static void main(String[] args) {
        int[] array = { 9, 2, 11, -3, 7, 21, 0 };
        sort2(array);
        for (int value : array) {
            System.out.print(value + " ");
        }
    }

    // 默写1，失败，思路不对，应该是找到最小的放在最前面
    private static void sort1(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {// n-1次
            int max = 0;
            for (int j = 0; j < array.length - i; j++) {// n,n-1,...,1 //这么写导致多比较n次
                if (array[j] > array[max] && max != j) {
                    max = j;
                }
            }
            int temp = array[array.length - i - 1];
            array[array.length - i - 1] = array[max];
            array[max] = temp;
        }
    }

    // 默写2，成功
    private static void sort2(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {// n-1次
            int min = i;
            for (int j = i + 1; j < array.length; j++) {// n-1,n-2,...,1
                if (array[j] < array[min]) {
                    min = j;
                }
            }
            if (min != i) {
                int temp = array[i];
                array[i] = array[min];
                array[min] = temp;
            }
        }
    }
}