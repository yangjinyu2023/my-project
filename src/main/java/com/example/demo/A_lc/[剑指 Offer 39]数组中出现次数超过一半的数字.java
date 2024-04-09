package com.example.demo.A_lc;//数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
//
// 
//
// 你可以假设数组是非空的，并且给定的数组总是存在多数元素。 
//
// 
//
// 示例 1: 
//
// 输入: [1, 2, 3, 2, 2, 2, 5, 4, 2]
//输出: 2 
//
// 
//
// 限制： 
//
// 1 <= 数组长度 <= 50000 
//
// 
//
// 注意：本题与主站 169 题相同：https://leetcode-cn.com/problems/majority-element/ 
//
// 
// Related Topics 数组 哈希表 分治 计数 排序 👍 297 👎 0


import java.util.HashMap;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution39 {
    public int majorityElement(int[] nums) {
        // 哈希表，时间O(n)，空间O(n)
        /*
        Map<String, Integer> map = new HashMap<>();
        for (int num : nums) {
            int count = map.get(String.valueOf(num)) == null ?
                    0 : map.get(String.valueOf(num));
            map.put(String.valueOf(num), ++count);
            if(count > (nums.length/2)){
                return num;
            }
        }
        return 0;
        */
        // 摩尔投票，时间O(n)，空间O(1)
        // 思想：假设不同值的元素分属不同阵营，
        // 每个元素在战斗时都会和对手同归于尽，
        // 那么主要元素阵营一定是最后剩下来的元素，因为它比总人数的一半还多。
        int votes = 0, major = 0;
        for (int num : nums) {
            if (votes == 0) {
                major = num;
            }
            votes += (num == major ? 1 : -1);
        }
        return major;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
