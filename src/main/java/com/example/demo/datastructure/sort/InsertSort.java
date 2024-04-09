package com.example.demo.datastructure.sort;

/**
 * 插入排序
 *
 * @author yangjinyu
 * @time 2021/6/7 17:49
 */
public class InsertSort {
    /**
     * <p>
     * 考虑已插入的数据有序，平均每次比较数为已插入数据的一半
     * </p>
     * <p>
     * 插入排序的平均比较开销为n(n-1)/4，优于选择排序和冒泡排序的n(n-1)/2
     * </p>
     * <p>
     * 插入排序平均的交换开销也是n(n-1)/4，选择排序最多只需要执行2*(n-1)次交换
     * </p>
     * <p>
     * 插入排序时间复杂度为O(N^2)
     * </p>
     */
    public static void sort(int[] array) {
        if (array == null) {
            throw new RuntimeException("array can not be null");
        }
        for (int i = 1; i < array.length; i++) {
            int j = i;
            // 记录要插入的数据
            int temp = array[i];
            while (j > 0 && array[j - 1] > temp) {// 使用temp而不使用array[i]
                array[j] = array[j - 1];// array[i]被顶了
                j--;
            }
            array[j] = temp;
        }
    }

    public static void main(String[] args) {
        int[] array = { 9, 2, 11, -3, 7, 21, 0, 11, -1 };
        sort1(array);
        for (int value : array) {
            System.out.print(value + " ");
        }
    }

    // 默写1，结果正常，但是不知道正确与否，按这个记忆吧
    private static void sort1(int[] array) {
        for (int i = 1; i < array.length; i++) {// 最坏n-1
            for (int j = i; j > 0; j--) {// 1,2,..,n-1
                if (array[j] < array[j - 1]) {// 把array[j]当做要插入的数，和之前比
                    int temp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = temp;
                } else {
                    break;// 大于之前的数则不再比较，因为前面的数有序，更之前的数会更小
                }
            }
        }
    }
}