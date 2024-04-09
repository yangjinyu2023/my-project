package com.example.demo.solutions;

import java.util.ArrayList;
import java.util.List;

class Solution4 {
    public static void main(String[] args) {
        new Solution4().findContinuousSequence(98160);
    }
    public int[][] findContinuousSequence(int target) {
        if (target == 1) {
            return new int[][]{{0, 1}};
        }
        List<int[]> list = new ArrayList<>();
        // 借助上一题思想
        int i = 1, j = target / 2 + 1;
        while (i < j) {
            // i+(i+1)+……+j，阶加公式为(i+j)*(j-i+1)/2
            int sum = (i + j) * (j - i + 1) / 2;
            if (sum == target) {
                int[] arr = new int[j - i + 1];
                for (int k = 0; k < arr.length; k++) {
                    arr[k] = i + k;
                }
                list.add(arr);
            }
            if (sum > target) {
                j--;
            } else {
                i++;
                j = target / 2 + 1;
            }
        }
        int[][] res = new int[list.size()][];
        for (int k = 0; k < list.size(); k++) {
            res[k] = list.get(k);
        }
        return res;
    }
}