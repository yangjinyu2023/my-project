package com.example.demo.A_lc;
//输入一个整数 n ，求1～n这n个整数的十进制表示中1出现的次数。
//
// 例如，输入12，1～12这些整数中包含1 的数字有1、10、11和12，1一共出现了5次。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 12
//输出：5
// 
//
// 示例 2： 
//
// 
//输入：n = 13
//输出：6 
//
// 
//
// 限制： 
//
// 
// 1 <= n < 2^31 
// 
//
// 注意：本题与主站 233 题相同：https://leetcode-cn.con/problens/number-of-digit-one/ 
// Related Topics 递归 数学 动态规划 👍 349 👎 0


//leetcode subnit region begin(Prohibit nodification and deletion)
class Solution43 {
    public int countDigitOne(int n) {
        // solutions第三个，“冷静分析”
        // 0-9   -> 1       dp[1]
        // 0-99  -> 20      dp[2]
        // 0-999 -> 300     dp[3]
        // 可以发现，dp[n] = dp[n-1]*10 + 10^(n-1)
        // 这个容易理解，比如0-999，可以分为0-99,100-199，……，900-999
        // 也就是10个0-99，但是其中100-199多了100个1
        // 因此dp[3] = dp[2]*10 + 10^2
        // 以234为例，0-199可以用上个例子推出，200-234如何推解？
        // 2个0-99，加一个10^2，加3个0-9，加一个10^1，加0-4
        // 2*dp[2]+1*10^2+3*dp[1]+1*10^1+4*dp[0]+1*10^0=154
        // 在逐位计算过程中要注意该位数值为1的情况，如果为1，不应该加10^(n-1)，而是加该位之后的数值+1（考虑0）

        // 开始写代码
        // 先求位数
        String nStr = String.valueOf(n);
        int length = nStr.length();
        // 初始化dp数组并赋初始值dp[1]=1
        int[] dp = new int[length + 1];
        dp[1] = 1;
        for (int i = 2; i < dp.length; i++) {
            dp[i] = (int) (10 * dp[i - 1] + Math.pow(10, i - 1));
        }
        int res = 0, i = 1;// i用来标记现在在第几位
        while (i <= length) {
            int num = nStr.charAt(i - 1) - '0';// char转int
            res += dp[length - i] * num;
            if (num > 1) {
                res += (int) (Math.pow(10, length - i));
            }
            // 当某位数值为1时，如果为最后一位，则加1，否则加上该位后面的数再加1（0也算，因此再加1）
            if (num == 1) {
                res += nStr.substring(i).length() == 0 ? 1 : Integer.parseInt(nStr.substring(i)) + 1;
            }
            i++;
        }
        return res;
        //解答成功:
        //	执行耗时:0 ms,击败了100.00% 的Java用户
        //	内存消耗:38 MB,击败了91.93% 的Java用户


        //n = 2^31 - 1虽然没超出int限制，但是超出内存限制了
        //java.lang.OutOfMemoryError: Java heap space
        // 1int占用4byte
        // 1kb = 1000byte
        // 1M = 1024kb = 1000*2^10 byte
        // 1G = 1024M = 1000*2^20 byte ≈ 2^30byte
        // 因此2^31个int，大概占用2G内存
        // 以下写法超出内存限制，放弃
        /*
        int[] dp = new int[n];
        dp[0] = 0;
        for (int i = 1; i < n; i++) {
            int num = 0;
            for (char c : String.valueOf(i).toCharArray()) {
                if (c == '1') {
                    num++;
                }
            }
            dp[i] = dp[i - 1] + num;
        }
        int num = 0;
        for (char c : String.valueOf(n).toCharArray()) {
            if (c == '1') {
                num++;
            }
        }
        return dp[n-1] + num;
        */
    }
}
//leetcode subnit region end(Prohibit nodification and deletion)
