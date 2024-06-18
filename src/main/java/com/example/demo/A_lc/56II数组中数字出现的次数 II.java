package com.example.demo.A_lc;

/**
 * 在一个数组 nums 中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [3,4,3,3]
 * 输出：4
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * 输入：nums = [9,1,7,9,7,9,7]
 * 输出：1
 * <p>
 * <p>
 * <p>
 * 限制：
 * <p>
 * <p>
 * 1 <= nums.length <= 10000
 * 1 <= nums[i] < 2^31
 * <p>
 * <p>
 * <p>
 * <p>
 * Related Topics 位运算 数组 👍 363 👎 0
 */

//leetcode submit region begin(Prohibit modification and deletion)
class Solution56II {
    // solutions第1个方法二
    // 很经典的二进制位运算，记住了！
    // 循环32次，循环中：右移，最低位和1做&与运算，可以得到二进制的每一位
    // 循环32次，循环中：左移，最低位和0或1做|或运算，可以组装一个二进制数
    public int singleNumber(int[] nums) {
        // 按位相加，逐位对3取余，得到只出现一次的数字
        // 按位相加后，每位可能的值：3n+1或者3n，n>=0
        int[] counts = new int[32];
        for (int num : nums) {
            for (int i = 0; i < 32; i++) {
                // 取num的每一位的值
                counts[i] += num & 1;// 加0或1
                num = num >>> 1;// num无符号右移
            }
        }

        int res = 0;
        // 如何恢复res？每次左移1位，最低位与0或1做或运算（很经典的位运算）
        for (int i = 31; i >= 0; i--) {
            res = res << 1;
            res |= counts[i] % 3;
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
