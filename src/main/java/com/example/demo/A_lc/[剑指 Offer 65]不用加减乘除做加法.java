package com.example.demo.A_lc;

/**
 * 写一个函数，求两个整数之和，要求在函数体内不得使用 “+”、“-”、“*”、“/” 四则运算符号。
 * <p>
 * <p>
 * <p>
 * 示例:
 * <p>
 * 输入: a = 1, b = 1
 * 输出: 2
 * <p>
 * <p>
 * <p>
 * 提示：
 * <p>
 * <p>
 * a, b 均可能是负数或 0
 * 结果不会溢出 32 位整数
 * <p>
 * <p>
 * Related Topics 位运算 数学 👍 335 👎 0
 */

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 位运算很奇妙
    // 进位c=(a&b)<<1，非进位n=a^b，容易得出a+b=n+c（随便举个例子就可以得出）
    // 不断的重复这个转换，直到c变成0（因为每次循环都左移1位），这样结果就是n了
    public int add(int a, int b) {
        while (b != 0) { // 当进位为 0 时跳出
            int c = (a & b) << 1;  // 进位和
            int n = a ^ b; // 非进位和
            // 重复这个转换
            a = n;
            b = c;
        }
        return a;
    }
}

//leetcode submit region end(Prohibit modification and deletion)
