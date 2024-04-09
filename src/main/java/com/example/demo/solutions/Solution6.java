package com.example.demo.solutions;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.lang.Integer;

/**
 * 给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。
 * <p>
 * 示例:
 * <p>
 * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 * 输出: [3,3,5,5,6,7]
 * 解释:
 * <p>
 * 滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 * 1 [3  -1  -3] 5  3  6  7       3
 * 1  3 [-1  -3  5] 3  6  7       5
 * 1  3  -1 [-3  5  3] 6  7       5
 * 1  3  -1  -3 [5  3  6] 7       6
 * 1  3  -1  -3  5 [3  6  7]      7
 * <p>
 * <p>
 * <p>
 * 提示：
 * <p>
 * 你可以假设 k 总是有效的，在输入数组不为空的情况下，1 ≤ k ≤ 输入数组的大小。
 * <p>
 * 注意：本题与主站 239 题相同：https://leetcode-cn.com/problems/sliding-window-maximum/
 * <p>
 * Related Topics 队列 滑动窗口 单调队列 堆（优先队列） 👍 471 👎 0
 */

//leetcode submit region begin(Prohibit modification and deletion)
class Solution6 {
    public int[] maxSlidingWindow(int[] nums, int k) {
        // 单调队列，进队O(1)，出队均摊时间O(1)，取最大值0(1)，空间O(2n)
        MaxQueue queue = new MaxQueue();
        int[] res = new int[nums.length - k + 1];
        for (int i = 0; i < nums.length; i++) {
            queue.offer(nums[i]);
            int j = i - k + 1;
            if (j >= 0) {
                res[j] = queue.max();
                queue.poll();
            }
        }
        return res;
    }

    static class MaxQueue {
        Queue<Integer> queue;
        Deque<Integer> deque;

        public MaxQueue() {
            queue = new LinkedList<>();
            deque = new LinkedList<>();
        }

        public void offer(Integer value) {
            queue.offer(value);
            while (!deque.isEmpty() && value > deque.peekLast()) {
                deque.pollLast();
            }
            deque.offerLast(value);
        }

        public int poll() {
            if (queue.isEmpty()) {
                return -1;
            }
            if (queue.peek().equals(deque.peekFirst())) {
                deque.pollFirst();
            }
            return queue.poll();
        }

        public int max() {
            if (deque.isEmpty()) {
                return -1;
            }
            return deque.peekFirst();
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
