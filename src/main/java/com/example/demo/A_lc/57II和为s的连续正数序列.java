package com.example.demo.A_lc;

import java.util.ArrayList;
import java.util.List;

/**
 * 输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。
 * <p>
 * 序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：target = 9
 * 输出：[[2,3,4],[4,5]]
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * 输入：target = 15
 * 输出：[[1,2,3,4,5],[4,5,6],[7,8]]
 * <p>
 * <p>
 * <p>
 * <p>
 * 限制：
 * <p>
 * <p>
 * 1 <= target <= 10^5
 * <p>
 * <p>
 * <p>
 * <p>
 * Related Topics 数学 双指针 枚举 👍 463 👎 0
 */

//leetcode submit region begin(Prohibit modification and deletion)
class Solution57II {

    public int[][] findContinuousSequence(int target) {
        // 写法是对的，可惜Time Limit Exceeded
        /*
        // 0不属于正整数！！
        List<int[]> list = new ArrayList<>();
        // 借助上一题思想
        int i = 1, j = target / 2 + 1;
        while (i < j) {
            // i+(i+1)+……+j，求和公式为(i+j)*(j-i+1)/2
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
                // 这里耗时了，每次i变化了重置j，时间复杂度大大增加，O(N^2)
                // 如何做到0(N)？将结果保存下来，每次结果减i或加j
            }
        }
        //int[][] res = new int[list.size()][];// 二维数组，第二个中括号可以不写长度，动态分配
        //for (int k = 0; k < list.size(); k++) {
        //    res[k] = list.get(k);
        //}
        //return res;
        // 上面5行代码，太low了
        return list.toArray(new int[0][]);// 记住这个写法
        */

        // 基于solutions第3个方法二，对上面写法进行改造
        List<int[]> list = new ArrayList<>();
        int i = 1, j = 2, sum = 3;
        while (i < j) {
            if (sum == target) {
                int[] arr = new int[j - i + 1];
                for (int k = i; k <= j; k++) {
                    arr[k - i] = k;
                }
                list.add(arr);
            }
            if(sum >= target) {// 这里有等号，继续下种情况
                sum -= i;
                i++;
            } else {
                j++;
                sum += j;
            }
        }
        return list.toArray(new int[0][]);// 记住这个写法
    }
}
//leetcode submit region end(Prohibit modification and deletion)
