package com.example.demo.A_lc;

/**
 * 假设把某股票的价格按照时间先后顺序存储在数组中，请问买卖该股票一次可能获得的最大利润是多少？
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: [7,1,5,3,6,4]
 * 输出: 5
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 * 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
 * <p>
 * <p>
 * 示例 2:
 * <p>
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 * <p>
 * <p>
 * <p>
 * 限制：
 * <p>
 * 0 <= 数组长度 <= 10^5
 * <p>
 * <p>
 * <p>
 * 注意：本题与主站 121 题相同：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-
 * stock/
 * <p>
 * Related Topics 数组 动态规划 👍 272 👎 0
 */

//leetcode submit region begin(Prohibit modification and deletion)
class Solution63 {
    public int maxProfit(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        // 暴力求解，第i天买入第j天卖出，时间复杂度O(N^2)
        /*
        int max = 0;
        for (int i = 0; i < prices.length; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                max = Math.max(max, prices[j] - prices[i]);
            }
        }
        max = max > 0 ? max : 0;
        return max;
        */
        // 动态规划
        /*
        int[] dp = new int[prices.length];
        int min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            min = Math.min(min, prices[i - 1]);
            int diff = prices[i] - min;
            dp[i] = Math.max(diff, dp[i - 1]);
        }
        return dp[prices.length - 1];
        */
        // 进一步简化
        int min = prices[0], profit = 0;
        for (int price : prices) {
            profit = Math.max(profit, price - min);
            min = Math.min(price, min);
        }
        return profit;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
