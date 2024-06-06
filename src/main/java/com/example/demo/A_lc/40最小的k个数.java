package com.example.demo.A_lc;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：arr = [3,2,1], k = 2
 * 输出：[1,2] 或者 [2,1]
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * 输入：arr = [0,1,2,1], k = 1
 * 输出：[0]
 * <p>
 * <p>
 * <p>
 * 限制：
 * <p>
 * <p>
 * 0 <= k <= arr.length <= 10000
 * 0 <= arr[i] <= 10000
 * <p>
 * <p>
 * Related Topics 数组 分治 快速选择 排序 堆（优先队列） 👍 461 👎 0
 */

//leetcode submit region begin(Prohibit modification and deletion)
class Solution40 {
    // topK问题
    // 1、快排，不需要对整个数组排序，当基准值位置刚好为k-1时，左边的是前k小
    // 2、大顶堆（前k小），小顶堆（前k大），最容易写的方式（记住这个）
    public int[] getLeastNumbers(int[] arr, int k) {
        if (k == 0 || arr.length == 0) {
            return new int[0];
        }
        
        // 大顶堆
        PriorityQueue<Integer> queue = new PriorityQueue<>((x, y) -> y - x);
        for (int num : arr) {
            if (queue.size() < k) {
                queue.offer(num);
            } else {
                // 只有值小于大顶堆的堆顶时（目前k个数中的最大值），才进堆
                if (num < queue.peek()) {
                    queue.poll();
                    queue.offer(num);
                }
            }
        }
        // 返回堆中的元素
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = queue.poll();
        }
        return res;

        // 快排解法
        //return quickSearch(arr, 0, arr.length - 1, k);
    }

    public int[] quickSearch(int[] arr, int l, int r, int k) {
        int pivotPosition = getPivotPosition(arr, l, r);
        // 当基准值位置刚好为k-1，左边的是前k小
        if (pivotPosition == k - 1) {
            return Arrays.copyOf(arr, k);// Arrays.copyOf第二个参数是length，所以传k
        }
        // 如果基准值位置不是k-1，那么到对应的半区去继续找基准值位置
        return pivotPosition > (k - 1) ? quickSearch(arr, l, pivotPosition - 1, k) : quickSearch(arr, pivotPosition + 1, r, k);
    }

    public int getPivotPosition(int[] arr, int l, int r) {
        int pivot = arr[l];
        int i = l, j = r + 1;
        while (true) {
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
        return j;
    }

    // 快排，一定一定要记住这个模板
    public void quickSort(int[] arr, int l, int r) {
        if (l >= r) {
            return;
        }
        int pivot = arr[l];
        int i = l, j = r + 1;
        while (true) {
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
//leetcode submit region end(Prohibit modification and deletion)
