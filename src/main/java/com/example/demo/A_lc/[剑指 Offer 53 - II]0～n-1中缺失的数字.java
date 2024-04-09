package com.example.demo.A_lc;

/**
 * 一个长度为n-1的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围0～n-1之内。在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，请找出这个
 * 数字。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: [0,1,3]
 * 输出: 2
 * <p>
 * <p>
 * 示例 2:
 * <p>
 * 输入: [0,1,2,3,4,5,6,7,9]
 * 输出: 8
 * <p>
 * <p>
 * <p>
 * 限制：
 * <p>
 * 1 <= 数组长度 <= 10000
 * <p>
 * Related Topics 位运算 数组 哈希表 数学 二分查找 👍 290 👎 0
 */

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int missingNumber(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            // 相等说明在右子数组
            if (nums[m] == m) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        // 最终返回不相等的首个（左边界）
        return l;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
