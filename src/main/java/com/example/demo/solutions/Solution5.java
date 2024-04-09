package com.example.demo.solutions;

/**
 * 把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s的所有可能的值出现的概率。
 * <p>
 * <p>
 * <p>
 * 你需要用一个浮点数数组返回答案，其中第 i 个元素代表这 n 个骰子所能掷出的点数集合中第 i 小的那个的概率。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: 1
 * 输出: [0.16667,0.16667,0.16667,0.16667,0.16667,0.16667]
 * <p>
 * <p>
 * 示例 2:
 * <p>
 * 输入: 2
 * 输出: [0.02778,0.05556,0.08333,0.11111,0.13889,0.16667,0.13889,0.11111,0.08333,0.0
 * 5556,0.02778]
 * <p>
 * <p>
 * <p>
 * 限制：
 * <p>
 * 1 <= n <= 11
 * <p>
 * Related Topics 数学 动态规划 概率与统计 👍 462 👎 0
 */

//leetcode submit region begin(Prohibit modification and deletion)
class Solution5 {
    public static void main(String[] args) {
        new Solution5().dicesProbability(2);
    }

    public double[] dicesProbability(int n) {
        // 动态规划，solutions第一个
        // n=1时可能出现的点数和：1,2,3,4,5,6
        // n=2时可能出现的点数和：2,3,4,5,6,7,8,9,10,11,12
        // n=3时可能出现的点数和：3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18
        // 容易得知，点数和范围n到6n

        // 不同点数对应场景：
        // n=1时： 1; 2; 3; 4; 5; 6;
        // n=2时： 2-(1,1); 3-(1,2)(2,1); 4-(1,3)(2,2)(3,1); 5-(1,4)(2,3)(3,2)(4,1);
        // 6-(1,5)(2,4)(3,3)(4,2)(5,1); 7-(1,6)(2,5)(3,4)(4,3)(5,2)(6,1); 8-(2,6)(3,5)(4,4)(5,3)(6,2)
        // 可以发现，n=2点数和为4时，是在n=1的基础上，点数和为1、2、3分别追加3、2、1
        // n=2点数和为5时，是在n=1的基础上，点数和为1、2、3、4分别追加4、3、2、1
        // n=2点数和为8时，是在n=1的基础上，点数和为2、3、4、5、6分别追加6、5、4、3、2

        // 可以得出状态转移方程：f(i,j) = ∑(6,k=1)f(n-1, j-k)
        // 其中i代表n，j代表点数和，f(i,j)代表i颗筛子在点数和j下的情况数，f(i,j)*(1/6)^n即为概率
        // 初始值
        int[][] dp = new int[n + 1][6 * n + 1];
        for (int i = 1; i <= 6; i++) {
            dp[1][i] = 1;
        }
        // f(i,j) = ∑(6,k=1)f(n-1, j-k)
        for (int i = 2; i <= n; i++) {
            for (int j = i; j <= 6 * i; j++) {
                for (int k = 1; k <= 6; k++) {
                    // j-k要在n=i-1的范围内
                    if (j - k >= i - 1 && j - k <= 6 * (i - 1)) {
                        dp[i][j] += dp[i - 1][j - k];
                    }
                }
            }
        }
        double[] res = new double[5 * n + 1];
        double ratio = Math.pow((1.0 / 6.0), n);
        for (int i = 0; i < res.length; i++) {
            res[i] = dp[n][n + i] * ratio;
        }
        return res;
        // 输出: [0.02778,0.05556,0.08333,0.11111,0.13889,0.16667,0.13889,0.11111,0.08333,0.05556,0.02778]
    }
}
//leetcode submit region end(Prohibit modification and deletion)
