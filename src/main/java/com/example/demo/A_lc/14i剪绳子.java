package com.example.demo.A_lc;//给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m-1] 。
//请问 k[0]*k[1]*...*k[m-1] 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18
//。 
//
// 示例 1： 
//
// 输入: 2
//输出: 1
//解释: 2 = 1 + 1, 1 × 1 = 1 
//
// 示例 2: 
//
// 输入: 10
//输出: 36
//解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36 
//
// 提示： 
//
// 
// 2 <= n <= 58 
// 
//
// 注意：本题与主站 343 题相同：https://leetcode-cn.com/problems/integer-break/ 
// Related Topics 数学 动态规划 👍 453 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution14i {
    // 动态规划
    // 对于的长度为n的绳子，当 n ≥ 2 时，可以剪成至少两个绳子。
    // 令 k 是剪出的第一段绳子，则剩下的部分是 n−k，n−k 可以不继续剪，或者继续剪成至少两段绳子。
    // （一个问题可以分解为相似的子问题因此想到动态规划）
    // 由于每个绳子长度对应的最大乘积取决于比它小的绳子对应的最大乘积，因此可以使用动态规划求解。
    public int cuttingRope(int n) {
        // dp[i]表示将长度为 i 的绳子剪成至少两段绳子（n>1）之后，这些绳子长度的最大乘积。
        // i最大为n，因此最后一个元素为dp[n]，因此长度指定为n+1。
        int[] dp = new int[n + 1];
        // 初始值（边界条件）
        // 题中条件，m>1，n>1，所以从2开始。
        dp[2] =1;
        // 状态转移方程
        // 当 i ≥ 2 时，假设对长度为 i 绳子剪出的第一段绳子长度是 j（1≤j<i），则有以下两种方案：
        // （1）将 i 剪成 j 和 i-j 长度的绳子，且 i−j 不再继续剪，此时的乘积是 j×(i−j) ；
        // （2）将 i 剪成 j 和 i−j 长度的绳子，且 i−j 继续剪成多段长度的绳子，此时的乘积是 j×dp[i−j] 。
        // 因此，当 j 固定时，有 dp[i]=max(j×(i−j),j×dp[i−j])。由于 j 的取值范围是 1 到 i ，需要遍历所有的 j 得到dp[i]的
        // 遍历顺序：由于dp[i]依赖于dp[i-j]，因此从前往后（自下向上）
        for (int i = 3; i <= n; i++) {
            int max = dp[i];
            for (int j = 1; j < i; j++) {
                max = Math.max(max, Math.max(j * (i - j), j * dp[i - j]));
            }
            dp[i] = max;
        }
        // 返回值
        // 最终得到dp[n]的值即为将长度为n的绳子拆分成至少两段绳子之后，这些绳子长度的最大乘积。
        return dp[n];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
