package com.example.demo.A_lc;//找出数组中重复的数字。
//
// 
//在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请
//找出数组中任意一个重复的数字。 
//
// 示例 1： 
//
// 输入：
//[2, 3, 1, 0, 2, 5, 3]
//输出：2 或 3 
// 
//
// 
//
// 限制： 
//
// 2 <= n <= 100000 
// Related Topics 数组 哈希表 排序 👍 875 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution3 {
    // 本题使用HashSet可解决，时间O(n)，空间O(n)
    // 题目中强调了数字在0~n-1范围内，根据此条件可做优化
    // 以下实现时间O(n)，空间O(1)
    public int findRepeatNumber(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while(i != nums[i]){
                // 让nums[i]到对的位置上
                // 如果对的位置和nums[i]元素重复，则返回
                if(nums[i] == nums[nums[i]]){
                    return nums[i];
                }
                // 否则进行交换
                int tmp = nums[nums[i]];
                nums[nums[i]] = nums[i];
                nums[i] = tmp;
            }
            // 跳出循环的时机：
            // （1）nums[i]位置刚好对，即i == nums[i]，此时继续下次for循环
            // （2）nums[i]位置不对，需要将nums[i]放到对的位置nums[nums[i]]，
            // 如果nums[i] == nums[nums[i]]，说明存在重复元素，直接返回
            // （由于数字在0~n-1范围内，所以一定存在重复的）
        }
        return -1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
