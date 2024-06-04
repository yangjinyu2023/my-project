package com.example.demo.A_lc;
//输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数在数组的前半部分，所有偶数在数组的后半部分。
//
// 
//
// 示例： 
//
// 
//输入：nums = [1,2,3,4]
//输出：[1,3,2,4] 
//注：[3,1,2,4] 也是正确的答案之一。 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 50000 
// 0 <= nums[i] <= 10000 
// 
// Related Topics 数组 双指针 排序 👍 241 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution21 {
    public int[] exchange(int[] nums) {
        // 快排思想
        int i = 0, j = nums.length - 1;
        while (true) {
            for (; i < (nums.length - 1) && isOdd(nums[i]); i++) {
            }
            for (; j > 0 && !isOdd(nums[j]); j--) {
            }
            if (i >= j) {
                break;
            }
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
        return nums;
    }

    // 判断一个数为偶数，与1做&运算等于0（&1相当于%2）
    public boolean isOdd(int num) {
        return (num & 1) > 0;
    }

    public int[] exchange111(int[] nums) {
        // 双指针
        // i向后，遇到奇数继续，遇到偶数和j换位置，
        // j向前，遇到偶数继续，遇到偶数和i换位置
        // i和j相遇退出
        int i = 0, j = nums.length;
        while (true) {
            while (i < nums.length - 1 && isOdd(nums[++i])) {
            }
            while (j > 0 && !isOdd(nums[--j])) {
            }
            if (i >= j) {
                break;
            }
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
        }
        return nums;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
