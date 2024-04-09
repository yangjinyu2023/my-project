package com.example.demo.A_lc;//实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xⁿ）。不得使用库函数，同时不需要考虑大数问题。
//
// 
//
// 示例 1： 
//
// 
//输入：x = 2.00000, n = 10
//输出：1024.00000
// 
//
// 示例 2： 
//
// 
//输入：x = 2.10000, n = 3
//输出：9.26100 
//
// 示例 3： 
//
// 
//输入：x = 2.00000, n = -2
//输出：0.25000
//解释：2⁻² = 1/2² = 1/4 = 0.25 
//
// 
//
// 提示： 
//
// 
// -100.0 < x < 100.0 
// -2³¹ <= n <= 2³¹-1 
// -10⁴ <= xⁿ <= 10⁴ 
// 
//
// 
//
// 注意：本题与主站 50 题相同：https://leetcode-cn.com/problems/powx-n/ 
// Related Topics 递归 数学 👍 311 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution16 {
    public double myPow(double x, int n) {
        /*
        // 快速幂，我会
        double res = 1.0;
        while (n > 0) {
            if ((n & 1) > 0) {
                res = res * x;
            }
            x *= x;
            n >>= 1;
        }
        return res;
        // n如果大于0，上面写法正确
        */
        // x^-n == 1/x^n
        // 由于x是double，做的除法运算。不要思维固化觉得/就是取整。
        long N = n;
        return n >= 0 ? quickPow(x, N) : 1 / quickPow(x, -N);
    }

    // 一定要注意细节，这里n需要类型long
    // 因为-2^31 <= n <= 2^31 - 1，那么-n就超出了int范围
    public double quickPow(double x, long n) {
        double res = 1.0;
        while (n > 0) {
            if ((n & 1) == 1) {
                res = res * x;
            }
            x *= x;
            n >>= 1;
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
