package com.example.demo.A_lc;
//统计一个数字在排序数组中出现的次数。
//
// 
//
// 示例 1: 
//
// 
//输入: nums = [5,7,7,8,8,10], target = 8
//输出: 2 
//
// 示例 2: 
//
// 
//输入: nums = [5,7,7,8,8,10], target = 6
//输出: 0 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 10⁵ 
// -10⁹ <= nums[i] <= 10⁹ 
// nums 是一个非递减数组 
// -10⁹ <= target <= 10⁹ 
// 
//
// 
//
// 注意：本题与主站 34 题相同（仅返回值不同）：https://leetcode-cn.com/problems/find-first-and-last-
//position-of-element-in-sorted-array/ 
// Related Topics 数组 二分查找 👍 333 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
// 看solutions第二个
class Solution53I {
    // 二分法，找到左右边界
    // 如何找左右边界？？（当时没想明白，本题记住）
    // 二分法找到第一个出现的数字，找到最后一个出现的数字
    public int search(int[] nums, int target) {
        if (nums.length == 0) {
            return 0;
        }
        // 使用递归
        /*
        int left = getLeft(nums, target, 0, nums.length - 1);
        int right = getRight(nums, target, 0, nums.length - 1);
        return right - left + 1; // 如果最终没找到，那么right+1=left
        */

        // 使用循环
        // 优化，简化为寻找target和target-1的右边界，相减
        // (target - 1) 一定存在吗？即便(target - 1)不存在，通过这种方式依然能正确处理边界情况，得到正确的统计结果。
        // 这是因为我们在寻找右边界时实际上是在找大于等于target的元素的最左侧位置，
        // 而寻找(target - 1)的右边界实质上是在定位所有小于target的元素的最右侧位置，
        // 从而间接确定了target的起始和结束位置，计算出其出现次数
        return getR(nums, target) - getR(nums, target - 1);
    }

    public int getR(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        int rightBound = -1; // 初始化右边界为-1，表示未找到目标值
        while (l <= r) {
            int m = l + (r - l) / 2;
            if (nums[m] <= target) {
                // 注意这里使用 `<=`，因为我们希望在等于的情况下也向右探索
                l = m + 1;
                if (nums[m] == target) {
                    rightBound = m;// 更新右边界
                }
            } else {
                r = m - 1;
            }
        }
        return rightBound;
    }

    public int getLeft(int[] nums, int target, int s, int e) {
        // 考虑边界情况，数组中只有一个元素，也要继续进行比较
        if (s > e) {
            return s;
        }
        int mid = s + (e - s) / 2;
        if (nums[mid] >= target) {
            return getLeft(nums, target, s, mid - 1);
        }
        if (nums[mid] < target) {
            return getLeft(nums, target, mid + 1, e);
        }
        return 0;
    }

    public int getRight(int[] nums, int target, int s, int e) {
        if (s > e) {
            return e;
        }
        int mid = s + (e - s) / 2;
        if (nums[mid] > target) {
            return getRight(nums, target, s, mid - 1);
        }
        if (nums[mid] <= target) {
            return getRight(nums, target, mid + 1, e);
        }
        return 0;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
