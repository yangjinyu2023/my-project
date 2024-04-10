package com.example.demo.A_lc;//请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。
//
// 
//
// 示例 1: 
//
// 输入: "abcabcbb"
//输出: 3 
//解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
// 
//
// 示例 2: 
//
// 输入: "bbbbb"
//输出: 1
//解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
// 
//
// 示例 3: 
//
// 输入: "pwwkew"
//输出: 3
//解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
//     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
// 
//
// 
//
// 提示： 
//
// 
// s.length <= 40000 
// 
//
// 注意：本题与主站 3 题相同：https://leetcode-cn.com/problems/longest-substring-without-
//repeating-characters/ 
// Related Topics 哈希表 字符串 滑动窗口 👍 461 👎 0


import java.util.*;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution48 {
    public int lengthOfLongestSubstring(String s) {
        if ("".equals(s)) {
            return 0;
        }
        // 动态规划，dp[i]表示以chars[i]结尾的最长不重复子符串长度
        // 优化1，使用滚动数组替代dp数组，dp[i]只和dp[i-1]有关，定义int p = 0; p=p<i-j?p+1:i-j; 循环从0开始
        // 优化2，j == -1和dp[i - 1] < i - j可以合并成一种情况
        int[] dp = new int[s.length()];
        // 存放char在字符串中的索引，这里是关键
        Map<Character, Integer> map = new HashMap<>();
        map.put(s.charAt(0), 0);
        int max = dp[0] = 1;
        int i, j;
        for (i = 1; i < s.length(); i++) {
            j = map.getOrDefault(s.charAt(i), -1);
            map.put(s.charAt(i), i);
            if (j == -1) {
                dp[i] = dp[i - 1] + 1;
            } else {
                if (dp[i - 1] >= i - j) {// 这里是关键
                    dp[i] = i - j;
                } else {
                    dp[i] = dp[i - 1] + 1;
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;

        // 滑动窗口（双指针）+哈希表，时间复杂度O(N^2)，时间复杂度O(2N)
        /*
        if ("".equals(s)) {
            return 0;
        }
        Set<Character> set = new HashSet<>();
        char[] chars = s.toCharArray();
        int slide = 0, max = 1;
        for (int i = 0; i < chars.length; i++) {
            if (!set.add(chars[i])) {
                while (slide < i) {
                    set.remove(chars[slide]);
                    if (chars[slide] == chars[i]) {
                        set.add(chars[i]);
                        slide++;
                        break;
                    }
                    slide++;
                }
            }
            max = Math.max(max, set.size());
        }
        return max;
       */
    }
}
//leetcode submit region end(Prohibit modification and deletion)
