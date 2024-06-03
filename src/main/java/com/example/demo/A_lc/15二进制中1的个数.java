package com.example.demo.A_lc;
//编写一个函数，输入是一个无符号整数（以二进制串的形式），返回其二进制表达式中数字位数为 '1' 的个数（也被称为 汉明重量).）。
//
// 
//
// 提示： 
//
// 
// 请注意，在某些语言（如 Java）中，没有无符号整数类型。在这种情况下，输入和输出都将被指定为有符号整数类型，并且不应影响您的实现，因为无论整数是有符号的
//还是无符号的，其内部的二进制表示形式都是相同的。 
// 在 Java 中，编译器使用 二进制补码 记法来表示有符号整数。因此，在上面的 示例 3 中，输入表示有符号整数 -3。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 11 (控制台输入 00000000000000000000000000001011)
//输出：3
//解释：输入的二进制串 00000000000000000000000000001011 中，共有三位为 '1'。
// 
//
// 示例 2： 
//
// 
//输入：n = 128 (控制台输入 00000000000000000000000010000000)
//输出：1
//解释：输入的二进制串 00000000000000000000000010000000 中，共有一位为 '1'。
// 
//
// 示例 3： 
//
// 
//输入：n = 4294967293 (控制台输入 11111111111111111111111111111101，部分语言中 n = -3）
//输出：31
//解释：输入的二进制串 11111111111111111111111111111101 中，共有 31 位为 '1'。 
//
// 
//
// 提示： 
//
// 
// 输入必须是长度为 32 的 二进制串 。 
// 
//
// 
//
// 注意：本题与主站 191 题相同：https://leetcode-cn.com/problems/number-of-1-bits/ 
// Related Topics 位运算 👍 255 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution15 {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        /*
        char[] chars = Integer.toBinaryString(n).toCharArray();
        int count = 0;
        for (int i = 0; i < chars.length; i++) {
            if(chars[i] == '1'){
                count++;
            }
        }
        return count;
        */
        int count=0;
        while (n!=0){
            if((n&1)==1){
                count++;
            }
            // 注意这里是无符号右移>>>，正数负数高位都补0
            // 有符号右移>>负数高位补1，永远达不到n==0的循环结束条件
            n>>>=1;
        }
        return count;
        /*
        // n&(n-1)，会让最右边的1变成0
        int count = 0;
        while(n != 0) {
            count++;
            n &= n - 1;
        }
        return count;
        */
        // 要判断一个数 n 是否是2的幂，可以利用上文提到的位运算技巧 n & (n - 1)。
        // 一个数是2的幂的特征是它的二进制表示中只有一个1，其余都是0。
        // 而 n & (n - 1) 的操作会将最低位的1变为0。
        // 因此，如果一个数是2的幂，那么它与它自己减1的结果的按位与操作将会得到0。
    }
}
//leetcode submit region end(Prohibit modification and deletion)
