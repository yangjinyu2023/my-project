package com.example.demo.datastructure.search;

/**
 * 二分查找
 *
 * @author yangjinyu
 * @time 2021/6/9 20:25
 */
public class BinarySearch {
    public static int search(int[] array, int target) {
        int low = 0;
        int high = array.length - 1;
        while (low <= high) {
            // low + (high - low)/2 此写法可以防止数组长度很大造成两者相加超过int范围
            int mid = low + (high - low)/2 ;
            if (array[mid] == target) {
                return mid;
            }
            if (array[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    public static int search_recursion(int[] array, int target, int low, int high) {
        int mid = (low + high) / 2;
        if (array[mid] == target) {
            return mid;
        }
        // 边界条件
        if (low > high) {
            return -1;
        } else {
            if (array[mid] > target) {
                return search_recursion(array, target, low, mid - 1);
            } else {
                return search_recursion(array, target, mid + 1, high);
            }
        }
    }

    public static void main(String[] args) {
        int[] array = {-3, 1, 3, 5, 7, 9, 11, 21, 22, 99, 108, 119};
        System.out.println(array[search_recursion(array, 119, 0, array.length - 1)]);
        System.out.println(array[search(array, 21)]);
        System.out.println(array[search1(array, 3, 0, array.length - 1)]);
        System.out.println(array[search2(array, 99)]);
    }

    // 默写1
    public static int search1(int[] array, int target, int start, int end) {
        if (start > end) {
            return -1;
        }
        int mid = start + (end - start) / 2;
        if (array[mid] == target) {
            return mid;
        }
        if (array[mid] > target) {
            return search1(array, target, start, mid - 1);
        } else {
            return search1(array, target, mid + 1, end);
        }
    }

    // 默写2
    public static int search2(int[] array, int target) {
        int start = 0, end = array.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (array[mid] == target) {
                return mid;
            }
            if (array[mid] > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }
}