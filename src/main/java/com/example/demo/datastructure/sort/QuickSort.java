package com.example.demo.datastructure.sort;

/**
 * 快速排序（分治策略）
 *
 * @author yangjinyu
 * @time 2021/6/10 16:42
 */
public class QuickSort {

    // 最容易记忆的快排，不会忘记！
    private void sort(int[] array, int start, int end) {

    }





    private static void sort_recursion(int[] array, int left, int right) {
        if (left < right) {
            int position = positionPivot(array, left, right);
            sort_recursion(array, left, position - 1);
            sort_recursion(array, position + 1, right);
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

    public static void main(String[] args) {
        int[] array = {9, 2, 11, 9, -3, 17, 3, 7, 9, 21, 0};
        //sort_recursion(array, 0 ,array.length -1);
        //QuickSort1.quickSort(array, 0, array.length - 1);
        QuickSort2.sort(array, 0, array.length - 1);
        for (int value : array) {
            System.out.print(value + " ");
        }
    }

    // 默写2，记忆这个
    static class QuickSort2 {
        // int[] array = {9, 2, 11, 9, -3, 17, 3, 7, 9, 21, 0};
        public static void sort(int[] array, int start, int end) {
            if (start < end) {// 递归结束条件
                int pivot = array[start];
                int i = start, j = end + 1;//使用--j
                while (true) {
                    while (i < end && array[++i] < pivot) {
                    }
                    while (j > start && array[--j] > pivot) {
                    }
                    if (i >= j) {// 这里有=号，相遇时也要退出
                        break;
                    }
                    swap(array, i, j);
                }
                swap(array, start, j);// 为什么是基准点和j交换？因为有可能没有相遇，i>j的情况，这时候array[j]<pivot<array[i]。要求基准点左边的都小于基准点。
                sort(array, start, j - 1);
                sort(array, j + 1, end);
            }
        }

        private static void swap(int[] array, int i, int j) {
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    // 默写1
    static class QuickSort1 {
        public static void quickSort(int[] arr, int l, int r) {
            if (l >= r) {
                return;
            }
            int pivot = arr[l];
            int i = l, j = r + 1;
            while (true) {
                // 只有和pivot比较大小时，不带等号（等于的交换），其余位置都带等号
                // 递归结束条件：l>=r
                // 两指针和边界比较：++i<=r;--j>=0
                // 两指针相遇：i>=j
                while (++i <= r && arr[i] < pivot) {
                }
                while (--j >= 0 && arr[j] > pivot) {
                }
                if (i >= j) {
                    break;
                }
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
            }
            arr[l] = arr[j];
            arr[j] = pivot;
            quickSort(arr, l, j - 1);
            quickSort(arr, j + 1, r);
        }
    }

    public void quickSort(int[] arr, int l, int r){
        if(l >= r){
            return;
        }
        int pivot = arr[l], i = l, j = r + 1;
        while(true){
            while (++i <= r && arr[i] < pivot){
            }
            while (--j >= 0 && arr[j] > pivot) {
            }
            if(i >= j){
                break;
            }
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
        arr[l] = arr[j];
        arr[j] = pivot;
        quickSort(arr, l, j - 1);
        quickSort(arr, j + 1, r);
    }
}