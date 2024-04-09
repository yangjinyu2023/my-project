package com.example.demo.algorithm.practice;

import org.junit.Assert;
import org.junit.Test;

/**
 * 动态规划练习
 *
 * @author yangjinyu
 * @time 2022/10/25 22:13
 */
public class DynamicPlanPractice {

    // 剑指offer-10-I
    // 写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项（即 F(N)）
    // 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
    public int fib(int n) {
        // f(n)=f(n-1)+f(n-2), f(1)=1, f(2)=1
        if(n < 2){
            return n;
        }
        int p, q = 0,r = 1;
        for (int i = 2; i <= n; i++) {
            p = q;
            q = r;
            r = (p + q)%1000000007;
        }
        return r;
    }

    // 剑指offer-10-II
    // 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
    // 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
    public int numWays(int n) {
        // f(n)=f(n-1)+f(n-2), f(1)=1, f(2)=2
        if(n < 2){
            return n;
        }
        int p, q = 1,r = 1;
        for (int i = 2; i <= n; i++) {
            p = q;
            q = r;
            r = (p + q)%1000000007;
        }
        return r;
    }

    // 剑指offer-14-I
    // 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），
    // 每段绳子的长度记为 k[0],k[1]...k[m-1] 。
    // 请问 k[0]*k[1]*...*k[m-1] 可能的最大乘积是多少？
    // 例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
    public int cuttingRope1(int n) {
        // dp[n]表示长度为n的绳子，乘积最大值
        int[] dp = new int[n+1];
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            int max = 0;
            // 剪下长度为j的一段，
            // 另一段可以不剪，dp[i] = j*(i-j)
            // 另一段可以继续剪，dp[i] = j*dp[i-j]
            for (int j = 1; j < i; j++) {
                max = Math.max(max, Math.max(j*(i-j), dp[i-j]));
            }
            dp[i] = max;
        }
        return dp[n];
    }

    // 剑指offer-42
    // 输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。
    // 1 <= arr.length <= 10^5
    // -100 <= arr[i] <= 100
    // 要求时间复杂度为O(n)
    public int maxSubArray(int[] nums) {
        // dp[i]表示以num[i]为结尾的连续子数组最大和
        int[] dp = new int[nums.length];
        int max = dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = dp[i - 1] > 0 ? dp[i - 1] + nums[i] : nums[i];
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    // 类似的，从矩阵array中左上角走到右上角，求路径和最大值
    // 定义dp[i][j]是从左上角走到(i,j)的位置路径和的最大值
    // 状态转移方程：dp[i][j] = max(dp[i-1][j],dp[i][j-1])+array[i][j]
    // 也就是走到(i,j)有两种走法，从(i-1,j)向下，从(i,j-1)向右
    // 初始值dp[0][0] = 0;dp[i][0] = dp[i-1][0] + array[i][0];dp[0][i] = dp[0][i-1] + arr[0][i];
    // 返回值dp(m-1)(n-1)，m=array.length，n=array[0].length
    public static int maxPaths(int[][] arr) {
        int m = arr.length;
        int n = arr[0].length;
        if (n == 0) {
            return 0;
        }

        int[][] dp = new int[m][n];
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
                dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]) + arr[i][j];
            }
        }
        return dp[m-1][n-1];
    }

    // 剑指offer-46
    // 给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，
    // 11 翻译成 “l”，……，25 翻译成 “z”。一个数字可能有多个翻译。
    // 请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。
    public int translateNum(int num) {
        String str = String.valueOf(num);
        int length = str.length();
        int[] dp = new int[length + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= length; i++) {
            int tmp = Integer.parseInt(str.substring(i-2, i));
            if(tmp >= 10 && tmp <= 25){
                dp[i] = dp[i-1] + dp[i-2];
            }else{
                dp[i] = dp[i-1];
            }
        }
        return dp[length];
    }

    // 剑指offer-47
    // 在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。
    // 你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、直
    // 到到达棋盘的右下角。给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？
    public int maxValue(int[][] grid) {
        int n = grid.length; int m = grid[0].length;
        int[][] dp = new int[n][m];
        dp[0][0] = grid[0][0];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(i == 0 && j == 0){
                    continue;
                }
                if(i == 0){
                    dp[0][j] = dp[0][j-1] + grid[0][j];
                }else if(j == 0){
                    dp[i][0] = dp[i-1][0] + grid[i][0];
                }else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
                }
            }
        }
        return dp[n-1][m-1];
    }

    // 剑指offer-49
    // 我们把只包含质因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。
    // 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。
    public int nthUglyNumber(int n) {
        int[] dp = new int[n];
        int p=0,q=0,r=0;
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            dp[i] = Math.min(Math.min(dp[p]*2,dp[q]*3),dp[r]*5);
            if(dp[i] == dp[p]*2){
                p++;
            }
            if(dp[i] == dp[q]*3){
                q++;
            }
            if(dp[i] == dp[r]*5){
                r++;
            }
        }
        return dp[n-1];
    }

    // 剑指offer-63
    // 假设把某股票的价格按照时间先后顺序存储在数组中，请问买卖该股票一次可能获得的最大利润是多少？
    public int maxProfit(int[] prices) {
        int min = prices[0], profit = 0;
        for (int price : prices) {
            profit = Math.max(price - min, profit);
            min = Math.min(price, min);
        }
        return profit;
    }

    // 剑指offer-13
    // 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。
    // 一个机器人从坐标 [0, 0] 的格子开始移动，
    // 它每次可以向左、右、上、下移动一格（不能移动到方格外），
    // 也不能进入行坐标和列坐标的数位之和大于k的格子。
    // 例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。
    // 但它不能进入方格 [35, 38]，因为3+5+3+8=19。
    // 请问该机器人能够到达多少个格子？
    public int movingCount(int m, int n, int k) {
        return 0;
    }


    // 剑指offer-43
    // 输入一个整数 n ，求1～n这n个整数的十进制表示中1出现的次数。
    // 例如，输入12，1～12这些整数中包含1 的数字有1、10、11和12，1一共出现了5次。
    public int countDigitOne(int n) {
        return 0;
    }

    @Test
    public void test(){
        Assert.assertEquals(12,nthUglyNumber(10));
    }
}