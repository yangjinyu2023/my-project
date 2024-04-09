package com.example.demo.A_lc;//给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m - 1]
// 。请问 k[0]*k[1]*...*k[m - 1] 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘
//积是18。 
//
// 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。 
//
// 
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
// 
//
// 提示： 
//
// 
// 2 <= n <= 1000 
// 
//
// 注意：本题与主站 343 题相同：https://leetcode-cn.com/problems/integer-break/ 
// Related Topics 数学 动态规划 👍 190 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution14ii {
    // 剪绳子I，i∈[2,58]；本题i∈[2,1000]
    // 相比于剪绳子I，本题目涉及 “大数越界情况下的求余问题” 。
    // solutions最后一个【笛子】的解答（一定要看）
    public int cuttingRope(int n) {
        /*
        // 计算过程中可能超过int的最大值，使用bigint（最多有19位）
        BigInteger[] dp = new BigInteger[n + 1];
        Arrays.fill(dp, BigInteger.valueOf(1));
        //dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                dp[i] = dp[i].max(BigInteger.valueOf(j * (i - j)).max(BigInteger.valueOf(j).multiply(dp[i - j])));
            }
        }
        return dp[n].mod(BigInteger.valueOf(1000000007)).intValue();
        */
        // 贪心算法
        // 将绳子全部按照长度为3进行切割乘积结果是最大的
        // (1) 在剩余长度等于4的时候，要取2 * 2，而不是3
        // (2) 在至少分出1段3的情况下，剩余n = 2的时候要取2；剩余n = 1的时候要取1
        if (n == 2) return 1;
        if (n == 3) return 2;
        if (n == 4) return 4;
        final int MOD = 1000000007;
        long res = 1;
        while (n > 4) {
            res = res * 3;
            res = res % MOD;
            n -= 3;
        }
        res = n * res % MOD;
        return (int) res;
        // (100000009 % 1000000007) * 3 和 (100000009 * 3）% 1000000007的结果是一样的
        // 因此可以在求解过程中取余，动态规划不能，因为涉及到数的比较
    }
}
//leetcode submit region end(Prohibit modification and deletion)
