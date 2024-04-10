package com.example.demo.A_lc;//数字以0123456789101112131415…的格式序列化到一个字符序列中。在这个序列中，第5位（从下标0开始计数）是5，第13位是1，第19位是4，
//等等。 
//
// 请写一个函数，求任意第n位对应的数字。 
//
// 
//
// 示例 1： 
//
// 输入：n = 3
//输出：3
// 
//
// 示例 2： 
//
// 输入：n = 11
//输出：0 
//
// 
//
// 限制： 
//
// 
// 0 <= n < 2^31 
// 
//
// 注意：本题与主站 400 题相同：https://leetcode-cn.com/problems/nth-digit/ 
// Related Topics 数学 二分查找 👍 268 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution44 {
    public int findNthDigit(int n) {
        /* 总体思路： n-->(找到位与数字分布关系)-->确定是几位数-->-->确定具体是哪个数-->确定是这个数的第几位 */
        // 将相同位数的数字划分为一组，用三个变量标识特诊：数字至少是一位数，初始化
        // 分组例如 [0-9] [10-99] [100-999] ...
        int start = 1;
        // 当前数字组的最小数，一位数是1，二位数是10...
        long digit = 1;
        // 当前数字组的位数，一位数是1，二位数是2...
        long count = start * digit * 9;
        // 当前数字组包含的数字个数，一位数是9，二位数是90，三位数是900...
        // 依次减去各组数字包含的位数，判断n落在哪组数字中
        // n的剩余量还大于当前count时，说明n属于位数更高的数字组，否则n就位于当前数字组中
        while (n > count) {
            n -= count;
            // n减去前序数字组的位数
            start *= 10;
            // 每个数字组的起始数字都是前一数字组起始位的10倍（10进制数的特性）
            digit += 1;
            // 每个数字组的位数都比前一个数字组多1（废话）
            count = start * digit * 9;
            // 计算当前数字组包含数字的个数
        }
        // 退出循环时 start digit count分别代表了n所在数字组所表示的特性
        // 定位n落在当前数字组中具体哪一个数字上
        // n-1目的：若n位于某个数最后一位即n与digit是整数倍关系，则会导致错位定位到下一位中（因为start是从1开始的）
        // 例如n=14时应该指向11的个位，若用n计算则：10 + (14-10) / 2 = 12
        long num = start + (n - 1) / digit;
        // 定位n落在当前数字的哪一位上，并获得该位上的数字值
        return Long.toString(num).charAt((int) ((n - 1) % digit)) - '0';
        // 小技巧，char转换int，c-'0'即可
    }
}
//leetcode submit region end(Prohibit modification and deletion)
