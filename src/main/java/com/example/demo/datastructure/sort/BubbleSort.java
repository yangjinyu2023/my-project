package com.example.demo.datastructure.sort;

/**
 * 冒泡排序
 *
 * @author yangjinyu
 * @time 2021/6/7 14:48
 */
public class BubbleSort {

    /**
     * <p>用二重循环实现，外循环变量设为i，内循环变量设为j。
     * <p>假如有n个数需要进行排序，则外循环重复n-1次，内循环依次重复n-1，n-2，...，1次。</p>
     * <p>每次进行比较的两个元素都是与内循环j有关的，它们可以分别用a[j]和a[j+1]标识</p>
     * <p>i的值依次为1,2,...,n-1，对于每一个i，j的值依次为0,1,2,...n-i 。</p>
     * <p>设置标志位判断是否发生过交换，在新一轮排序开始时，检查此标志，若上一次没有做过交换数据，则结束排序；否则进行排序。</p>
     * <p>最快n-1，最慢n(n-1）/2，时间复杂度为O(n^2)</p>
     */
    private static void sort(int[] array) {
        if (array == null) {
            throw new RuntimeException("array can not be null");
        }
        boolean noSwap = true;
        // 关键点在于，由于要比较j和j+1，所以array.length要减1
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    noSwap = false;
                }
            }
            if (noSwap) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[] array = {9, 2, 11, -3, 7, 21, 0};
        sort(array);
        for (int value : array) {
            System.out.print(value + " ");
        }
    }
}