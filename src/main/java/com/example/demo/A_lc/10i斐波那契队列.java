package com.example.demo.A_lc;//写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项（即 F(N)）。斐波那契数列的定义如下：
//
// 
//F(0) = 0,   F(1) = 1
//F(N) = F(N - 1) + F(N - 2), 其中 N > 1. 
//
// 斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。 
//
// 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 2
//输出：1
// 
//
// 示例 2： 
//
// 
//输入：n = 5
//输出：5
// 
//
// 
//
// 提示： 
//
// 
// 0 <= n <= 100 
// 
// Related Topics 记忆化搜索 数学 动态规划 👍 372 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution10i {
    public int fib(int n) {
        // 递归解法很简单，效率低，而且可能Stack Overflow
        /*
        if (n > 1) {
            return fib(n - 1) + fib(n - 2);
        }
        return n;
        */
        // 动态规划法（递推思想），时间O(n)，空间O(1)
        // 状态转移方程：F(N) = F(N - 1) + F(N - 2), 其中 N > 1
        // 边界条件：F(0) = 0, F(1) = 1
        if (n < 2) {
            return n;
        }
        final int MOD = 1000000007;
        int p = 0, q = 0, r = 1;
        for (int i = 2; i <= n; i++) {
            // 滚动数组
            p = q;
            q = r;
            r = (p + q) % MOD;
        }
        return r;
        // 矩阵快速幂，时间O(logN)，空间O(1)，解释看Solutions中第一个
    }

}
//leetcode submit region end(Prohibit modification and deletion)
