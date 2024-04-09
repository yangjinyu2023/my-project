package com.example.demo.A_lc;

/**
 * 输入一个递增排序的数组和一个数字s，在数组中查找两个数，使得它们的和正好是s。如果有多对数字的和等于s，则输出任意一对即可。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[2,7] 或者 [7,2]
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * 输入：nums = [10,26,30,31,47,60], target = 40
 * 输出：[10,30] 或者 [30,10]
 * <p>
 * <p>
 * <p>
 * <p>
 * 限制：
 * <p>
 * <p>
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^6
 * <p>
 * <p>
 * Related Topics 数组 双指针 二分查找 👍 200 👎 0
 */

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] twoSum(int[] nums, int target) {
        // 数组递增，使用双指针法
        int i = 0, j = nums.length - 1;
        while (i < j) {// i==j时候放弃，题意中不能使用同一个数字
            int value = nums[i] + nums[j];
            if (value == target) {
                return new int[]{nums[i], nums[j]};
            }
            if (value < target) {
                i++;
            } else {
                j--;
            }
        }
        return new int[0];

        // 二分查找，不是最优解
        /*
        for (int i = 0; i < nums.length; i++) {
            if (binarySearch(nums, i, nums.length - 1, target - nums[i])) {
                return new int[]{nums[i], target - nums[i]};
            }
        }
        return new int[0];*/
    }

    public boolean binarySearch(int[] nums, int s, int e, int target) {
        if (s > e) {
            return false;
        }
        int mid = s + (e - s) / 2;
        if (nums[mid] == target) {
            return true;
        }
        if (nums[mid] < target) {
            return binarySearch(nums, mid + 1, e, target);
        } else {
            return binarySearch(nums, s, mid - 1, target);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
