package com.example.demo.A_lc;//在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。
//
// 
//
// 示例 1: 
//
// 输入: [7,5,6,4]
//输出: 5 
//
// 
//
// 限制： 
//
// 0 <= 数组长度 <= 50000 
// Related Topics 树状数组 线段树 数组 二分查找 分治 有序集合 归并排序 👍 797 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution51 {
    public int count = 0;

    public int reversePairs(int[] nums) {
        // solutions倒数第二个
        // 归并排序，在合并期间统计逆序对
        int[] tmp = new int[nums.length];
        sort(nums, 0, nums.length - 1, tmp);
        return count;

        // Time Limit Exceeded
        // Time O(N^2) SPACE O(N)
        /*
        if(nums.length == 0){
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = 0;
        for (int i = 1; i < nums.length; i++) {
            int count = 0;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    count++;
                }
            }
            dp[i] = dp[i - 1] + count;
        }
        return dp[nums.length - 1];
        */
    }

    public void sort(int[] nums, int start, int end, int[] tmp) {
        if (start >= end) {
            return;
        }
        int mid = start + (end - start) / 2;
        sort(nums, start, mid, tmp);
        sort(nums, mid + 1, end, tmp);
        merge(nums, start, mid, end, tmp);
    }

    // 为什么采用归并排序，在归并阶段可以统计逆序对呢？举个例子：7,5,6,4,8,1
    // 首先拆分：
    // 7,5,6    4,8,1
    // 7,5  6   4,8  1
    // 7  5  6  4  8  1
    // 开始合并：
    // 5,7  6   4,8  1
    // A   B    C   D  在原数组中的顺序，A->B  C->D
    // 因此判断A中有几个比B大的，C中有几个比D大的，就能统计逆序对数量
    // 左子数组索引i，右子数组索引j，如果nums[i] > nums[j]，
    // 那么说明i到mid+1区间内的都比nums[j]大（因为子数组有序）
    // 所以 count += mid + 1 - 1
    public void merge(int[] nums, int start, int mid, int end, int[] tmp) {
        int i = start, j = mid + 1, t = 0;
        while (i <= mid && j <= end) {
            if (nums[i] <= nums[j]) {
                tmp[t++] = nums[i++];
            } else {
                count += mid + 1 - i;// 在归并排序基础上加上这句，统计逆序对（记住，很经典）
                tmp[t++] = nums[j++];
            }
        }
        while (i <= mid) {
            tmp[t++] = nums[i++];
        }
        while (j <= end) {
            tmp[t++] = nums[j++];
        }
        t = 0;
        while (start <= end) {
            nums[start++] = tmp[t++];
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
