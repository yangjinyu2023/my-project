package com.example.demo.algorithm.practice;

import java.util.Arrays;

/**
 * 排序算法练习，考的最多的还是快排和归并
 *
 * @author yangjinyu
 * @time 2022/10/25 10:17
 */
public class SortPratice {
    // 快速排序
    public void quickSort(int arr[], int left, int right) {
        if (left >= right) {// 递归结束带等号
            return;
        }
        int pivot = arr[left];
        int i = left, j = right + 1;
        while (true) {
            while (i < right && arr[++i] < pivot) {// ++i，跳过left，因为arr[left]就是pivot
            }
            while (j > 0 && arr[--j] > pivot) {// --j，因为定义的j = right + 1
            }
            if (i >= j) {// 循环结束带等号，如果i >= j要结束循环，arr[i]和arr[j]不需要做交换了
                break;
            }
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
        arr[left] = arr[j];// j作为新基准点，因为可能会i>j，这样arr[i] > pivot，不满足pivot左边的都小于pivot条件
        arr[j] = pivot;
        quickSort(arr, left, j - 1);
        quickSort(arr, j + 1, right);
    }

    // 归并排序
    public void mergeSort(int[] arr, int left, int right, int[] tmp) {
        if (left >= right) {
            return;
        }
        int mid = (right - left) / 2 + left;
        mergeSort(arr, left, mid, tmp);// 注意这里不是mid-1
        mergeSort(arr, mid + 1, right, tmp);
        int i = left, j = mid + 1, t = 0;
        while (i <= mid && j <= right) {
            if (arr[i] < arr[j]) {
                tmp[t++] = arr[i++];
            }
            else {
                tmp[t++] = arr[j++];
            }
        }
        while (i <= mid) {
            tmp[t++] = arr[i++];
        }
        while (j <= right) {
            tmp[t++] = arr[j++];
        }
        t = 0;
        while (left <= right) {
            arr[left++] = tmp[t++];
        }
    }

    // 希尔排序
    public void shellSort(int[] arr) {
        // 快速排序的升级版
        int h = 0;
        while (h <= arr.length / 3) {
            h = 3 * h + 1;
        }
        // 间隔gap的元素，进行一次插入排序
        for (int gap = h; gap > 0; gap = (gap - 1) / 3) {
            // 以下为插入排序
            for (int i = gap; i < arr.length; i++) {
                for (int j = i; j - gap >= 0; j -= gap) {
                    if (arr[j] < arr[j - gap]) {
                        int tmp = arr[j];
                        arr[j] = arr[j - gap];
                        arr[j - gap] = tmp;
                    }
                }
            }
        }
    }

    // 插入排序
    // 每一步将一个待排序的元素，插入到前面已经排好序的有序序列中去
    public void insertSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = tmp;
                }
                else {
                    break;
                }
            }
        }
    }

    // 冒泡排序
    // 一次冒泡过程：从左向右，如果左面的大于右边的就交换，最终将最大的冒泡到最右边
    // 对前面N-1个数继续冒泡，一共冒泡N-1轮
    public void bubbleSort(int[] arr) {
        boolean noSwap = true;
        for (int i = 0; i < arr.length - 1; i++) {// i < arr.length - 1 ? 因为只需要比较N-1次
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                    noSwap = false;
                }
            }
            if (noSwap) {// 如果一次冒泡过程，没有产生交换，说明已经有序
                break;
            }
        }
    }

    // 选择排序
    // 一次选择过程：从左向右，找到最小的数的下标，然后和最左边的交换
    // 对后面N-1个数继续选择，一共选择N-1次，比冒泡的交换次数少
    public void choiceSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {// i < arr.length - 1 ? 因为只需要比较N-1次
            int minInx = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minInx]) {
                    minInx = j;
                }
            }
            if (minInx != i) {
                int tmp = arr[i];
                arr[i] = arr[minInx];
                arr[minInx] = tmp;
            }
        }
    }

    public static void main(String[] args) {
        SortPratice p = new SortPratice();
        int[] arr = new int[] { 3, 1, -9, 6, -1, 4, 5, 2, 12, 7, -2, 5 };
        p.quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
        arr = new int[] { 3, 1, -9, 6, -1, 4, 5, 2, 12, 7, -2, 5 };
        p.mergeSort(arr, 0, arr.length - 1, new int[arr.length]);
        System.out.println(Arrays.toString(arr));
        arr = new int[] { 3, 1, -9, 6, -1, 4, 5, 2, 12, 7, -2, 5 };
        p.shellSort(arr);
        System.out.println(Arrays.toString(arr));
        arr = new int[] { 3, 1, -9, 6, -1, 4, 5, 2, 12, 7, -2, 5 };
        p.insertSort(arr);
        System.out.println(Arrays.toString(arr));
        arr = new int[] { 3, 1, -9, 6, -1, 4, 5, 2, 12, 7, -2, 5 };
        p.bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
        arr = new int[] { 3, 1, -9, 6, -1, 4, 5, 2, 12, 7, -2, 5 };
        p.choiceSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}