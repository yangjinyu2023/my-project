package com.example.demo.A_lc;

/**
 * 给定一个数组 A[0,1,…,n-1]，请构建一个数组 B[0,1,…,n-1]，其中 B[i] 的值是数组 A 中除了下标 i 以外的元素的积, 即 B[i]
 * =A[0]×A[1]×…×A[i-1]×A[i+1]×…×A[n-1]。不能使用除法。
 * <p>
 * <p>
 * <p>
 * 示例:
 * <p>
 * <p>
 * 输入: [1,2,3,4,5]
 * 输出: [120,60,40,30,24]
 * <p>
 * <p>
 * <p>
 * 提示：
 * <p>
 * <p>
 * 所有元素乘积之和不会溢出 32 位整数
 * a.length <= 100000
 * <p>
 * <p>
 * Related Topics 数组 前缀和 👍 254 👎 0
 */

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 美团面试考过此题呢！
    public int[] constructArr(int[] a) {
        int length = a.length;
        if (length == 0) {
            return new int[0];
        }
        // 考虑动态规划
        // [1,2,3,4,5]，dp[3]=(1*2)*(4*5)
        // 如果能记录下左、右边的乘积，可以得出状态转移方程：
        // dp[i] = left[i-1]*right[i+1];
        // 对于left，同样可以使用动态规划，状态转移方程为：
        // left[i] = left[i-1] * a[i-1]; 初始值left[0] = 1;
        // 对于right，同理：
        // right[i-1] = right[i] * a[i]; 初始值left[length -1] = 1;
        int[] dp = new int[length];
        int[] left = new int[length];
        left[0] = 1;
        for (int i = 1; i < length; i++) {
            left[i] = left[i - 1] * a[i - 1];
        }
        int[] right = new int[length];
        right[length - 1] = 1;
        for (int i = length - 2; i >= 0; i--) {
            right[i] = right[i + 1] * a[i + 1];
        }
        for (int i = 0; i < length; i++) {
            dp[i] = left[i] * right[i];
        }
        return dp;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
