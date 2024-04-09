package com.example.demo.datastructure.sort.optimize;

/**
 * 优化的快速排序
 * <p>
 * 优化1：
 * 假设我们是对一个逆序数组进行排序，选取第一个元素作为基准点，
 * 即最大的元素是基准点，那么第一次循环，左游标要执行到最右边，
 * 而右游标执行一次，然后两者进行交换。这也会划分成很多的子数组。
 * 理想状态下，应该选择被排序数组的中值数据作为基准，也就是说一半的数大于基准数，
 * 一般的数小于基准数，这样会使得数组被划分为两个大小相等的子数组，对快速排序来说，
 * 拥有两个大小相等的子数组是最优的情况。
 * </p>
 * <p>
 * 优化2：
 * 当数组长度小于M的时候（high-low <= M）， 不进行快排，而进行插入排序。、
 * 转换参数M的最佳值和系统是相关的，一般来说， 5到15间的任意值在多数情况下都能令人满意。
 * </p>
 *
 * @author yangjinyu
 * @time 2021/6/10 16:42
 */
public class OptimizeQuickSort {

    private static void sort(int[] array, int left, int right) {
        if (left < right) {
            int position = positionPivot(array, left, right);
            sort(array, left, position - 1);
            sort(array, position + 1, right);
        }
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private static int positionPivot(int[] array, int left, int right) {
        int i = left;
        int j = right + 1;
        int pivot = array[left];
        // 优化1
        if(array.length >= 3){
            pivot = medianOf3(array, left, right);
        }
        while (true) {
            while (i < right && array[++i] < pivot) {
            }
            while (j > 0 && array[--j] > pivot) {
            }
            // i>=j说明相遇
            if (i >= j) {
                break;
            } else {
                // 没相遇，则交换
                swap(array, i, j);
            }
        }
        swap(array, left, j);
        return j;
    }

    private static int medianOf3(int[] array, int left, int right) {
        int center = (right - left) / 2 + left;
        // 首先保证array[right]是最大
        if(array[left] > array[right]){
            swap(array, left, right);
        }
        if(array[center] > array[right]){
            swap(array, center, right);
        }
        // 保证array[left]是中值，即array[center] <  array[left] < array[right]
        if(array[center] > array[left]){
            swap(array, center, left);
        }
        return array[left];
    }

    public static void main(String[] args) {
        int[] array = new int[10000];
        for (int i = 0; i < 10000; i++) {
            array[i] = (int) (Math.random() * 10000);
        }

        sort(array, 0, array.length - 1);
        for (int value : array) {
            System.out.print(value + " ");
        }
    }
}