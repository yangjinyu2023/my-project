package com.example.demo.solutions;

public class Solution7 {
    public static void main(String[] args) {
        int[] nums = new int[]{5, 7, 7, 8, 8, 10};
        new Solution7().search(nums, 8);
    }

    // 二分法，找到左右边界
    // 如何找左右边界？找到第一个出现的数字，找到最后一个出现的数字
    // 看solutions第二个
    public int search(int[] nums, int target) {
        if (nums.length == 0) {
            return 0;
        }
        int left = getLeft(nums, target, 0, nums.length - 1);
        int right = getRight(nums, target, 0, nums.length - 1);
        return right - left + 1;
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