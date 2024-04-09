package com.example.demo.A_lc;

/**
 * 写一个函数 StrToInt，实现把字符串转换成整数这个功能。不能使用 atoi 或者其他类似的库函数。
 * <p>
 * <p>
 * <p>
 * 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。
 * <p>
 * 当我们寻找到的第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，作为该整数的正负号；假如第一个非空字符是数字，则直接将其与之后连续的
 * 数字字符组合起来，形成整数。
 * <p>
 * 该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略，它们对于函数不应该造成影响。
 * <p>
 * 注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换。
 * <p>
 * 在任何情况下，若函数不能进行有效的转换时，请返回 0。
 * <p>
 * 说明：
 * <p>
 * 假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−2³¹, 231 − 1]。如果数值超过这个范围，请返回 INT_MAX (231 −
 * 1) 或 INT_MIN (−2³¹) 。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "42"
 * 输出: 42
 * <p>
 * <p>
 * 示例 2:
 * <p>
 * 输入: "   -42"
 * 输出: -42
 * 解释: 第一个非空白字符为 '-', 它是一个负号。
 *      我们尽可能将负号与后面所有连续出现的数字组合起来，最后得到 -42 。
 * <p>
 * <p>
 * 示例 3:
 * <p>
 * 输入: "4193 with words"
 * 输出: 4193
 * 解释: 转换截止于数字 '3' ，因为它的下一个字符不为数字。
 * <p>
 * <p>
 * 示例 4:
 * <p>
 * 输入: "words and 987"
 * 输出: 0
 * 解释: 第一个非空字符是 'w', 但它不是数字或正、负号。
 * 因此无法执行有效的转换。
 * <p>
 * 示例 5:
 * <p>
 * 输入: "-91283472332"
 * 输出: -2147483648
 * 解释: 数字 "-91283472332" 超过 32 位有符号整数范围。
 *      因此返回 INT_MIN (−2³¹) 。
 * <p>
 * <p>
 * <p>
 * <p>
 * 注意：本题与主站 8 题相同：https://leetcode-cn.com/problems/string-to-integer-atoi/
 * <p>
 * Related Topics 字符串 👍 177 👎 0
 */

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int strToInt(String str) {
        char[] cs = str.toCharArray();
        int i = 0, n = cs.length;
        //跳过空格
        while (i < n && cs[i] == ' ') {
            i++;
        }
        long res = 0;
        int t = 0;
        //t是标识，用来记录正负
        if (i < n && cs[i] == '+') {
            t = 1;
            i++;
        } else if (i < n && cs[i] == '-') {
            t = -1;
            i++;
        }
        //如果是数字，则进行追加
        while (i < n && cs[i] >= '0' && cs[i] <= '9') {
            res = res * 10 + (cs[i++] - '0');
            //追加过程如果超过最大值，就判断正负进行返回
            if (res > Integer.MAX_VALUE)
                return (t == -1) ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        }
        //判断是否有符号，有符号则乘上
        if (t != 0)
            res = res * t;
        return (int) res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
