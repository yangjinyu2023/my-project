package com.example.demo.solutions;


class Solution3 {

    public static void main(String[] args) {
        new Solution3().reversePairs(new int[]{7,5,6,4});
    }
    public int count = 0;

    public int reversePairs(int[] nums) {
        // solutions第二个
        // 归并排序，在合并期间统计逆序对
        int[] tmp = new int[nums.length];
        sort(nums, 0, nums.length - 1, tmp);
        return count;

        // Time Limit Exceeded
        // Time O(N^2) SPACE O(N)
        /*
        if(nums.length == 0){
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = 0;
        for (int i = 1; i < nums.length; i++) {
            int count = 0;
            for (int j = 0; j < i; j++) {
                if (nums[j] > nums[i]) {
                    count++;
                }
            }
            dp[i] = dp[i - 1] + count;
        }
        return dp[nums.length - 1];
        */
    }

    public void sort(int[] nums, int start, int end, int[] tmp) {
        if (start >= end) {
            return;
        }
        int mid = start + (end - start) / 2;
        sort(nums, start, mid, tmp);
        sort(nums, mid + 1, end, tmp);
        merge(nums, start, mid, end, tmp);
    }

    public void merge(int[] nums, int start, int mid, int end, int[] tmp) {
        int i = start, j = mid + 1, t = 0;
        while (i <= mid && j <= end) {
            if (nums[i] > nums[j]) {
                tmp[t++] = nums[i++];
                count++;
            } else {
                tmp[t++] = nums[j++];
            }
        }
        while (i <= mid) {
            tmp[t++] = nums[i++];
        }
        while (j <= end) {
            tmp[t++] = nums[j++];
            count++;
        }
        t = 0;
        while (start <= end) {
            nums[start++] = tmp[t++];
        }
    }
}