package com.example.demo.A_lc;//输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。
//
// 要求时间复杂度为O(n)。 
//
// 
//
// 示例1: 
//
// 输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
//输出: 6
//解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。 
//
// 
//
// 提示： 
//
// 
// 1 <= arr.length <= 10^5 
// -100 <= arr[i] <= 100 
// 
//
// 注意：本题与主站 53 题相同：https://leetcode-cn.com/problems/maximum-subarray/ 
//
// 
// Related Topics 数组 分治 动态规划 👍 562 👎 0


import java.util.PriorityQueue;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution42 {
    public int maxSubArray(int[] nums) {
        // 动态规划
        // 1、dp数组，定义元素代表的含义（这一步是最难的，也是最关键的）
        // 2、状态转移方程
        // 3、初始值
        // 4、返回值
        int length = nums.length;
        // dp[i]，表示以num[i]为结尾的连续子数组最大和
        int[] dp = new int[length];
        // 状态转移方程 dp[i] = dp[i-1] > 0 ? dp[i-1] + nums[i] : nums[i];
        // 初始值
        int max = dp[0] = nums[0];
        for (int i = 1; i < length; i++) {
            dp[i] = dp[i - 1] > 0 ? dp[i - 1] + nums[i] : nums[i];
            max = Math.max(max, dp[i]);
        }
        // 返回值
        return max;
    }
    // 类似的，从矩阵array中左上角走到右上角，求路径和最大值
    // 定义dp[i][j]是从左上角走到(i,j)的位置路径和的最大值
    // 状态转移方程：dp[i][j] = max(dp[i-1][j],dp[i][j-1])+array[i][j]
    // 也就是走到(i,j)有两种走法，从(i-1,j)向下，从(i,j-1)向右
    // 初始值dp[0][0] = 0;dp[i][0] = dp[i-1][0] + array[i][0];dp[0][i] = dp[0][i-1] + arr[0][i];
    // 返回值dp(m-1)(n-1)，m=array.length，n=array[0].length

    // 下面的实现是取路径和最小值，且不考虑array[i][j]<0
    public static int uniquePaths(int[][] arr) {
        int m = arr.length;
        int n = arr[0].length;
        if (m <= 0 || n <= 0) {
            return 0;
        }

        int[][] dp = new int[m][n]; //
        // 初始化
        dp[0][0] = arr[0][0];
        // 初始化最左边的列
        for(int i = 1; i < m; i++){
            dp[i][0] = dp[i-1][0] + arr[i][0];
        }
        // 初始化最上边的行
        for(int i = 1; i < n; i++){
            dp[0][i] = dp[0][i-1] + arr[0][i];
        }
        // 推导出 dp[m-1][n-1]
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + arr[i][j];
            }
        }
        return dp[m-1][n-1];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
