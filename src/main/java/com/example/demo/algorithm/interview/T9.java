package com.example.demo.algorithm.interview;

import java.util.Arrays;

/**
 * 最长上升子序列 leetcode 300
 * </p>
 * 字符串的全排列 剑指 Offer 38
 *
 * @author yangjinyu
 * @time 2022/10/19 22:55
 */
public class T9 {
    public static void main(String[] args) {
        System.out.println("最长递增子序列" + new T9().lengthOfLIS(new int[] { 10, 9, 2, 5, 3, 7, 101, 18 }));
    }

    public int lengthOfLIS(int[] nums) {
        int max = 1;
        int length = nums.length;
        // 定义dp数组，dp[k]代表以num[k]结尾的最长递增子序列长度
        int[] dp = new int[length];
        // 初始值
        // dp[0] = 1;
        Arrays.fill(dp, 1);
        // 状态转移方程
        // 观察推理，dp[i]是0到i-1中，nums值小于nums[i]的，dp值加1，取最大值
        // 比如[10,9,2,5,3,7,101,18]，
        // 其中7，可以是[2,7] [5,7] [3,7] [2,5,7] [2,3,7]
        // 分别是在dp[2] dp[5] dp[3]上加1，取最大值3
        for (int i = 1; i < length; i++) {
            // dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}