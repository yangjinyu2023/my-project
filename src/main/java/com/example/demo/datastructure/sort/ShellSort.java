package com.example.demo.datastructure.sort;

/**
 * 希尔排序，非稳定排序算法
 *
 * @author yangjinyu
 * @time 2021/6/10 16:42
 */
public class ShellSort {

    // 直接插入排序算法在某些情况下的效率是很很高的，
    // 1.当我们的记录本身就是基本有序（小的关键字基本在前面，大的基本在后面）的，
    // 我们只需要少量的插入操作，就可以完成排序。
    // 2.还有就是记录数比较少时，直接插入的优势也是比较明显的。
    // 希尔排序可以说就是直接插入排序的升级版，希尔排序通过构造上面的2个条件，使排序更快的实现。
    // 希尔排序是怎样来构造这2个条件的呢？
    // 这里采用跳跃分割的策略：将相距某个“增量”的记录组成一个子序列，
    // 这样才能保证子序列内分别进行直接插入排序后得到的结果是基本有序而不是局部有序。
    // 希尔排序算法利用分组粗调的方式减少了插入排序算法的工作量，使得算法的平均复杂度低于O(N^2)
    private static void sort(int[] array) {
        int h = 1;
        while (h <= array.length / 3) {
            h = h * 3 + 1;// 1,4,13,40......knuth间隔序列 3h+1
        }
        for (int gap = h; gap > 0; gap = (gap - 1) / 3) {
            for (int i = gap; i < array.length; i++) {
                for (int j = i; j - gap >= 0; j -= gap) {// 对每个增量间隔进行排序
                    if (array[j] < array[j - gap]) {
                        int temp = array[j];
                        array[j] = array[j - gap];
                        array[j - gap] = temp;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] array = {9, 2, 11, -3, 7, 9, 21, 0, -9, 1};
        sort(array);
        for (int value : array) {
            System.out.print(value + " ");
        }
    }

    // 默写1
    private static void sort1(int[] array) {
        int h = 1;
        while (h <= array.length / 3) {
            h = h * 3 + 1;
        }
        for (int gap = h; gap > 0; gap = (gap - 1) / 3) {
            for (int i = gap; i < array.length; i++) {
                for (int j = i; j > gap - 1; j -= gap) {
                    if (array[j] < array[j - gap]) {
                        int temp = array[j];
                        array[j] = array[j - gap];
                        array[j - gap] = temp;
                    }
                }
            }
        }
    }
}