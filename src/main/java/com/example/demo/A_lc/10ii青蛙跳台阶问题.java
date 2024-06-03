package com.example.demo.A_lc;//一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
//
// 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。 
//
// 示例 1： 
//
// 输入：n = 2
//输出：2
// 
//
// 示例 2： 
//
// 输入：n = 7
//输出：21
// 
//
// 示例 3： 
//
// 输入：n = 0
//输出：1 
//
// 提示： 
//
// 
// 0 <= n <= 100 
// 
//
// 注意：本题与主站 70 题相同：https://leetcode-cn.com/problems/climbing-stairs/ 
//
// 
// Related Topics 记忆化搜索 数学 动态规划 👍 301 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution10ii {
    public int numWays(int n) {
        // 加上有f(n)种跳法，先考虑边界，最后一跳，要么跳2层，要么跳1层
        // 如果跳2层，还有f(n-2)种跳法；如果跳1层，还有f(n-1)种跳法
        // f(n)=f(n-1)+f(n-2)，f(0)=1，f(1)=1，f(2)=2
        // 转换为斐波那契问题，初始值不同
        if (n < 2) {
            return 1;
        }
        final int MOD = 1000000007;
        int p = 0, q = 1, r = 1;
        for (int i = 2; i <= n; i++) {
            p = q;
            q = r;
            r = (p + q) % MOD;
        }
        return r;
    }

    public int numWays111(int n) {
        // f(n)=f(n-1)+f(n-2),f(0)=1,f(1)=1,f(2)=2
        //int[] dp = new int[n + 1];
        //dp[0] = 1;
        //dp[1] = 1;
        //for (int i = 2; i <= n; i++) {
        //    dp[i] = dp[i-1] + dp[i-2];
        //}
        //return dp[n];
        if(n<=2){
            return 1;
        }
        int a,b=1,c=1;
        for (int i = 2; i <= n; i++) {
            a = b;
            b = c;
            c = a+b;
        }
        return c;
    }

    public static void main(String[] args) {
        System.out.println(new Solution10ii().numWays111(3));
    }

}
//leetcode submit region end(Prohibit modification and deletion)
