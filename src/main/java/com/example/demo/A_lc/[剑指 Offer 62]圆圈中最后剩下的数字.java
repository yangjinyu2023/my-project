package com.example.demo.A_lc;

/**
 * 0,1,···,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字（删除后从下一个数字开始计数）。求出这个圆圈里剩下的最后一个数字。
 * <p>
 * 例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，则删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入: n = 5, m = 3
 * 输出: 3
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * 输入: n = 10, m = 17
 * 输出: 2
 * <p>
 * <p>
 * <p>
 * <p>
 * 限制：
 * <p>
 * <p>
 * 1 <= n <= 10^5
 * 1 <= m <= 10^6
 * <p>
 * <p>
 * Related Topics 递归 数学 👍 653 👎 0
 */

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 约瑟夫环问题，用动态规划解决
    // 只看懂“胎教毕业”的solution了。。看来我也就胎教毕业
    // 拿n=5,m=3举例，
    // 0,1,2,3,4 它剩的最后数组记为dp[5]
    // 找到第一个删除的位置m%n=3%5=3，剩余3,4,0,1，它剩的最后数组记为dp'[4]，dp'[4]=dp[5]
    // 0,1,2,3 它剩的最后数组记为dp[4]
    // dp'[4] 3,4,0,1
    // dp[4]  0,1,2,3
    // 可以得出dp[n]=dp'[n-1]=(dp[n-1,m]+m)%n
    public int lastRemaining(int n, int m) {
        // dp[n,m] = 0, (n=1)
        // dp[n,m] = (dp[n-1,m]+m)%n,  (n>=1)
        int[] dp = new int[n + 1];
        dp[1] = 0;
        for (int i = 2; i <= n; i++) {
            dp[i] = (dp[i - 1] + m) % i;
        }
        return dp[n];
        // dp[n]只和dp[n-1]有关，这种可以简化成下面写法，空间复杂度从O(N)降为O(1)
        /*
        int x = 0;
        for (int i = 2; i <= n; i++) {
            x = (x + m) % i;
        }
        return x;
        */
    }
}
//leetcode submit region end(Prohibit modification and deletion)
