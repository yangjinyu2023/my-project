package com.example.demo.A_lc;
//输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
//
// 
//
// 示例 1: 
//
// 输入: [10,2]
//输出: "102" 
//
// 示例 2: 
//
// 输入: [3,30,34,5,9]
//输出: "3033459" 
//
// 
//
// 提示: 
//
// 
// 0 < nums.length <= 100 
// 
//
// 说明: 
//
// 
// 输出结果可能非常大，所以你需要返回一个字符串而不是整数 
// 拼接起来的数字可能会有前导 0，最后结果不需要去掉前导 0 
// 
// Related Topics 贪心 字符串 排序 👍 498 👎 0


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution45 {
    public String minNumber(int[] nums) {
        StringBuilder res = new StringBuilder();
        String[] strs = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strs[i] = String.valueOf(nums[i]);
        }
        // 自定义排序，每个元素都按照规则进行排序
        Arrays.sort(strs, (x, y) -> (x + y).compareTo(y + x));
        for (String str : strs) {
            res.append(str);
        }
        return res.toString();

        // 贪心算法（局部最优解）
        // 自顶向下，每一步上都要保证能获得局部最优解
        // 由此产生的全局解有时不一定是最优的，所以贪心算法不要回溯

        // 分析此题，找局部最优解
        // 分析前两个元素x和y，如果"x"+"y"<"y"+"x"，那么x应该在y前
        // 分析前三个元素，将前两个元素看做一个整体，转换成分析两个元素
        // 最终按照排序结果，拼接字符串返回即可
    }
}
//leetcode submit region end(Prohibit modification and deletion)
