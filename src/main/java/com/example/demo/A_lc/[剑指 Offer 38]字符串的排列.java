package com.example.demo.A_lc;//输入一个字符串，打印出该字符串中字符的所有排列。
//
// 
//
// 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。 
//
// 
//
// 示例: 
//
// 输入：s = "abc"
//输出：["abc","acb","bac","bca","cab","cba"]
// 
//
// 
//
// 限制： 
//
// 1 <= s 的长度 <= 8 
// Related Topics 字符串 回溯 👍 579 👎 0


import java.util.HashSet;
import java.util.Set;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution38 {
    Set<String> set;
    char[] c;// 每层递归，找到固定位置，变换元素
    // 比如[a,b,c]，第一层递归固定第一个位置，变换元素为a、b、c
    // 如果位置1是a，那么第二层递归固定第二个位置，变换元素为b、c
    // 以此类推，边界条件为固定的位置为最后一个，将字符串加入结果集合

    public String[] permutation(String s) {
        // 排列组合问题
        // 第一个元素有n种方式，第二个n-1种，……，一共n!种
        this.set = new HashSet<>();
        this.c = s.toCharArray();
        recur(0);
        return set.toArray(new String[set.size()]);//将集合转成对应泛型数组，这个写法要记住
    }

    public void recur(int fixed) {
        if (fixed == c.length - 1) {
            set.add(String.valueOf(c));// 此时说明c中元素全部固定完毕，是一种排列方案
            // String.valueOf可以传入char[]，这个写法记住
            return;
        }
        for (int i = fixed; i < c.length; i++) {
            swap(fixed, i);// 给固定位置，变换元素
            recur(fixed + 1);
            swap(fixed, i);// 再变回来
        }
    }

    public void swap(int fixed, int i) {
        if(fixed != i) {
            char tmp = c[fixed];
            c[fixed] = c[i];
            c[i] = tmp;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
