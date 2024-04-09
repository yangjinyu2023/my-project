package com.example.demo.A_lc;

/**
 * 求 1+2+...+n ，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入: n = 3
 * 输出: 6
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * 输入: n = 9
 * 输出: 45
 * <p>
 * <p>
 * <p>
 * <p>
 * 限制：
 * <p>
 * <p>
 * 1 <= n <= 10000
 * <p>
 * <p>
 * Related Topics 位运算 递归 脑筋急转弯 👍 520 👎 0
 */

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 本题要点：用&&短路、||短路，可以替代if
    public int sumNums(int n) {
        // 脑筋急转弯都上来了。。限制很多，看答案，用递归
        // 无法用if，因此使用&&短路（&&前面为false，就不会执行&&后面语句）
        boolean x = n > 1 && (n += sumNums(n - 1)) > 0;
        /*
        if(n > 1){
            n += sumNums(n-1);
        }
        */
        return n;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
