package com.example.demo.A_lc;

/**
 * 一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [4,1,4,6]
 * 输出：[1,6] 或 [6,1]
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * 输入：nums = [1,2,10,4,1,4,3,3]
 * 输出：[2,10] 或 [10,2]
 * <p>
 * <p>
 * <p>
 * 限制：
 * <p>
 * <p>
 * 2 <= nums.length <= 10000
 * <p>
 * <p>
 * <p>
 * <p>
 * Related Topics 位运算 数组 👍 678 👎 0
 */

//leetcode submit region begin(Prohibit modification and deletion)
class Solution56I {
    // solutions第二个，位运算
    public int[] singleNumbers(int[] nums) {
        // 如果a=b，那么a^b=0
        // 整型数组 nums 里除一个数字之外，其他数字都出现了两次，这种直接异或，得到结果
        // 本题说除了两个数字x、y外，其他数字都出现两次，
        // 因此需要拆分成两个数组，一个包含x，一个包含y
        // 举个例子，
        // 假设x=1、y=2，二级制0001、0010，异或后变成0011
        // 从低位到高位找到第一个为1的位置，x和y在该位置上一定一个为1一个为0
        // 通过该位置可以将数组分成两份（num&0001是否为0）

        int tmp = 0, x = 0, y = 0;
        for (int num : nums) {
            tmp = tmp ^ num;
        }
        // 此时a=x^y
        int offset = 1;
        // 找到第一个为1的位置
        while ((tmp & offset) == 0) {
            // 如果未找到，往高位移动一位，即从低位往高位找第一个为1的位置
            offset = offset << 1;
        }
        for (int num : nums) {
            // 相当于把数组分成两份，一份包含x，一份包含y
            if ((num & offset) == 0) {
                x = x ^ num;
            } else {
                y = y ^ num;
            }
        }
        return new int[]{x, y};
    }
}
//leetcode submit region end(Prohibit modification and deletion)
