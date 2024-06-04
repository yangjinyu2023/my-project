package com.example.demo.datastructure.sort;

/**
 * 归并排序（分治策略）
 *
 * @author yangjinyu
 * @time 2021/6/10 10:40
 */
public class MergeSort {

    public static void merge(int[] array, int start, int mid, int end, int[] temp) {
        int i = start;// 左序列指针
        int j = mid + 1;// 右序列指针
        int t = 0;// 临时数组指针
        while (i <= mid && j <= end) {
            if (array[i] <= array[j]) {
                temp[t++] = array[i++];
            }
            else {
                temp[t++] = array[j++];
            }
        }
        // 将左边剩余元素填充进temp中
        while (i <= mid) {
            temp[t++] = array[i++];
        }
        // 将右序列剩余元素填充进temp中
        while (j <= end) {
            temp[t++] = array[j++];
        }
        // 将temp中的元素全部拷贝到原数组中
        t = 0;
        while (start <= end) {
            array[start++] = temp[t++];
        }
    }

    /**
     * <P>
     * 归并排序是稳定排序，时间复杂度为O(N*logN)
     * </P>
     * <P>
     * 缺点：它需要的空间是原始数组空间的两倍
     * </P>
     */
    public static void sort(int[] array, int start, int end, int[] temp) {
        if (start < end) {
            int mid = start + (end - start) / 2;
            sort(array, start, mid, temp);
            sort(array, mid + 1, end, temp);
            merge(array, start, mid, end, temp);
        }
    }

    public static void main(String[] args) {
        int[] array = { 9, 2, 11, -3, 7, 9, 21, 0 };
        // 在排序前，先建好一个长度等于原数组长度的临时数组，避免递归中频繁开辟空间
        int[] temp = new int[array.length];
        mergeSort(array, 0, array.length - 1, temp);
        for (int value : array) {
            System.out.print(value + " ");
        }
    }

    public static void mergeSort(int[] arr, int s, int e, int[] tmp) {
        if (s >= e) {
            return;
        }
        int m = (e - s) / 2 + s;
        mergeSort(arr, s, m, tmp);
        mergeSort(arr, m + 1, e, tmp);
        int i = s, j = m + 1, t = 0;
        while (i <= m && j <= e) {
            if (arr[i] <= arr[j]) {
                tmp[t++] = arr[i++];
            }
            else {
                tmp[t++] = arr[j++];
            }
        }
        while (i <= m) {
            tmp[t++] = arr[i++];
        }
        while (j <= e) {
            tmp[t++] = arr[j++];
        }
        t = 0;
        while (s <= e) {
            arr[s++] = tmp[t++];
        }
    }

    // 所以归并的比较，都带=
    public static void mergeSort111(int[] arr, int s, int m, int e, int[] tmp){
        if(s>=e){
            return;
        }
        int i = s, j = m+1, t = 0;
        while(i<=m && j <=e){
            if (arr[i]<=arr[j]){
                tmp[t++] = arr[i++];
            }else{
                tmp[t++] = arr[j++];
            }
        }
        while(i<=m){
            tmp[t++] = arr[i++];
        }
        while(j<=e){
            tmp[t++] = arr[j++];
        }
        t=0;
        while(s <= e){
            arr[s++] = tmp[t++];
        }
    }
}