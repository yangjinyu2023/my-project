package com.example.demo.A_lc;
//给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。一个数字可
//能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。 
//
// 
//
// 示例 1: 
//
// 输入: 12258
//输出: 5
//解释: 12258有5种不同的翻译，分别是"bccfi", "bwfi", "bczi", "mcfi"和"mzi" 
//
// 
//
// 提示： 
//
// 
// 0 <= num < 2³¹ 
// 
// Related Topics 字符串 动态规划 👍 458 👎 0


import java.sql.SQLOutput;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution46 {
    public int translateNum(int num) {
        // dp[i]表示前i个数字的翻译方式数量
        // 状态转移方程
        // 如果后两位<=25，dp[i] = dp[i-1] + dp[i-2]
        // 如果后两位>25，dp[i] = dp[i-1]
        // 初始值
        String str = String.valueOf(num);
        int[] dp = new int[str.length() + 1];
        dp[0] = 1;// 没有数字时，翻译方式数量为1，这个容易搞错
        dp[1] = 1;
        for (int i = 2; i <= str.length(); i++) {
            // 这里弄不明白为啥是i-2，
            // 可以举个例子，例如i==2，num是12，那么startIndex=i-2==0，往后截取两位，因此endIndex=i
            // String.substring(s,e)，看源码可知，s是定位，e-s得到length代表向后截取几位，这个一定要记住！
            if (str.substring(i - 2, i).compareTo("10") >= 0 && str.substring(i - 2, i).compareTo("25") <= 0) {
                dp[i] = dp[i - 1] + dp[i - 2];
            } else {
                dp[i] = dp[i - 1];
            }
        }
        return dp[str.length()];
        // 优化，f(n)=f(n-1)+f(n-2)，类似于斐波那契数列、青蛙跳台阶
        // 可以使用滚动数组替代dp[n]，空间复杂度从0(N)变成0(1)
        // int p=1,q=1,r;
        //  for (int i = 2; i <= str.length(); i++) {
        //     r=condition?p+q:q;
        //     p=q;
        //     q=r;
        // }
        // return q;
    }


    public int translateNum111(int num) {
        //输入: 12258
        //输出: 5
        //解释: 12258有5种不同的翻译，分别是"bccfi", "bwfi", "bczi", "mcfi"和"mzi"
        //dp[i]代表前i个数字的翻译方式数量
        //dp[i]=dp[i-1]+dp[i-2]
        String numStr = String.valueOf(num);
        int[] dp = new int[numStr.length() + 1];
        dp[1] = 1;
        int n = Integer.parseInt(numStr.substring(0, 2));
        //n>=10这个条件别忘记
        dp[2] = n >= 10 && n <= 25 ? 2 : 1;
        for (int i = 3; i <= numStr.length(); i++) {
            n = Integer.parseInt(numStr.substring(i - 2, i));
            dp[i] = dp[i - 1] + (n >= 10 && n <= 25 ? dp[i - 2] : 0);
        }
        return dp[numStr.length()];
        // 为什么要判断>=10，举个例子101，可以翻译bab，不能翻译成ab，因为01没有对应的翻译
    }

    public static void main(String[] args) {
        System.out.println(new Solution46().translateNum111(12258));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
