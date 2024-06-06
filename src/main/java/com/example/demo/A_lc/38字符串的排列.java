package com.example.demo.A_lc;
//输入一个字符串，打印出该字符串中字符的所有排列。
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
        return set.toArray(new String[0]);//将集合转成对应泛型数组，这个写法要记住
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
        if (fixed != i) {
            char tmp = c[fixed];
            c[fixed] = c[i];
            c[i] = tmp;
        }
    }

    //回溯是一种在解决问题时常用的算法思想，特别是在处理约束满足、组合优化和搜索等问题时非常有效。
    // 它的核心思想是在解决问题的过程中，通过尝试不同的路径（决策）来寻找解决方案。
    // 当发现当前路径无法达到目标状态或不符合约束条件时，算法会“回退”到上一步，撤销之前的部分决策，然后尝试其他可能的分支。
    // 这一过程反复进行，直到找到一个解决方案或遍历完所有可能的路径。

    //回溯算法的基本框架通常包括以下几个关键步骤：
    //1.选择路径：在当前状态下选择一个可选项（分支）进行探索。
    //2.做出决策：基于所选路径做出一个决策，通常是将一个元素放入解的某个位置或选择一个特定的值。
    //3.验证约束：检查当前的决策是否导致违反任何问题的约束条件，或者是否达到了目标状态。
    //  如果当前路径符合所有约束且未达到目标，继续深入下一层决策。
    //  如果发现当前路径不可行（违反约束或达到死路），则进入回溯阶段。
    //4.回溯：撤销上一步的决策，回到上一状态，然后尝试下一个可选项。这一步是回溯算法名称的由来，意味着“回头”去尝试其他路径。
    //5.剪枝：在回溯过程中，通过有效的剪枝策略（如约束传播、边界检测等）提前排除掉不可能产生解决方案的分支，可以显著减少搜索空间，提高算法效率。
    //回溯法广泛应用于许多经典问题，如八皇后问题、图的着色问题、旅行商问题（TSP）、组合求和问题、迷宫寻路等。
    //通过灵活运用回溯策略，可以在可接受的时间复杂度内解决这些问题。
}
//leetcode submit region end(Prohibit modification and deletion)
