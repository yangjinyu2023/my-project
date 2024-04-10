package com.example.demo.A_lc;

import java.util.Arrays;

/**
 * 从若干副扑克牌中随机抽 5 张牌，判断是不是一个顺子，即这5张牌是不是连续的。2～10为数字本身，A为1，J为11，Q为12，K为13，而大、小王为 0 ，可以
 * 看成任意数字。A 不能视为 14。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * <p>
 * 输入: [1,2,3,4,5]
 * 输出: True
 * <p>
 * <p>
 * <p>
 * 示例 2:
 * <p>
 * <p>
 * 输入: [0,0,1,2,5]
 * 输出: True
 * <p>
 * <p>
 * <p>
 * 限制：
 * <p>
 * 数组长度为 5
 * <p>
 * 数组的数取值为 [0, 13] .
 * <p>
 * Related Topics 数组 排序 👍 260 👎 0
 */

//leetcode submit region begin(Prohibit modification and deletion)
class Solution61 {
    public boolean isStraight(int[] nums) {
        /*
        // 插入排序，从大到小排列
        for (int i = 0; i < nums.length; i++) {
            int j = i, tmp = nums[i];
            while (j > 0) {
                if (nums[j - 1] >= tmp) {
                    break;
                }
                nums[j] = nums[j - 1];
                j--;
            }
            nums[j] = tmp;
        }
        // 万能牌数量、牌间隔超过1的超额总数
        int wildcard = 0, diffSum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                wildcard++;
            } else if (i > 0) {
                int diff = nums[i - 1] - nums[i];
                // 出现相同的非万能牌，不连续
                if (diff == 0) {
                    return false;
                }
                diffSum += diff > 1 ? diff - 1 : 0;
            }
        }
        return wildcard - diffSum >= 0;
        */
        // 写的复杂了，只需要：
        // （1）比较最大的和最小的（非0），两者差不超过5
        // （2）非0的不能有重复的
        Arrays.sort(nums);
        int index = 0;
        for (int i = 0; i < 4; i++) {
            if (nums[i] == 0) {
                index++;
            } else {
                if (nums[i] == nums[i + 1]) {
                    return false;
                }
            }
        }
        return nums[4] - nums[index] < 5;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
