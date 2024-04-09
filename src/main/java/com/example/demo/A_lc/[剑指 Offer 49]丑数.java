package com.example.demo.A_lc;//我们把只包含质因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。
//
// 
//
// 示例: 
//
// 输入: n = 10
//输出: 12
//解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。 
//
// 说明: 
//
// 
// 1 是丑数。 
// n 不超过1690。 
// 
//
// 注意：本题与主站 264 题相同：https://leetcode-cn.com/problems/ugly-number-ii/ 
// Related Topics 哈希表 数学 动态规划 堆（优先队列） 👍 363 👎 0


import java.util.HashSet;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int nthUglyNumber(int n) {
        // solutions第二个
        int[] dp = new int[n];
        dp[0] = 1;
        int p = 0, q = 0, r = 0;
        for (int i = 1; i < n; i++) {
            dp[i] = Math.min(Math.min(dp[p] * 2, dp[q] * 3), dp[r] * 5);
            if (dp[i] == dp[p] * 2) {
                p++;
            }
            if (dp[i] == dp[q] * 3) {
                q++;
            }
            if (dp[i] == dp[r] * 5) {
                r++;
            }
        }
        return dp[n - 1];
        // 算法不对!
        /*
        Set<Integer> set = new HashSet<>();
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        set.add(1);
        for (int i = 2; i <= n; i++) {
            int add = 1;
            while (true) {
                int num = dp[i - 1] + add;
                if (num % 2 == 0 && set.contains(num / 2)) {
                    dp[i] = num;
                    break;
                }
                if (num % 3 == 0 && set.contains(num / 3)) {
                    dp[i] = num;
                    break;
                }
                if (num % 5 == 0 && set.contains(num / 5)) {
                    dp[i] = num;
                    break;
                }
                add++;
            }
            set.add(dp[i]);
        }
        return dp[n];
        */
    }
}
//leetcode submit region end(Prohibit modification and deletion)
