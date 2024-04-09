package com.example.demo.algorithm.interview;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 剑指offer 38，输入一个字符串，打印出该字符串中字符的所有排列。
 * </p>
 * 输入：s = "abc" 输出：["abc","acb","bac","bca","cab","cba"]
 *
 * 2023-10-31 没写出来，背住 https://zhuanlan.zhihu.com/p/572440340
 * @author yangjinyu
 * @time 2022/10/20 16:10
 */
public class T10 {
    public static void main(String[] args) {
        String[] s = new T10().permutation("abc");
        Arrays.stream(s).forEach(System.out::println);
    }

    Set < String > set = new HashSet <>();

    char[] arr;

    public String[] permutation(String s) {
        arr = s.toCharArray();
        recursion(0);
        return set.toArray(new String[s.length()]);
    }

    //分析：这是个递归求解的问题。递归算法有四个特性：
    // （1)必须有可达到的终止条件，否则程序将陷入死循环；
    // （2）子问题在规模上比原问题小；
    // （3）子问题可通过再次递归调用求解；
    // （4）子问题的解应能组合成整个问题的解。

    //此题递归解法：
    //从集合中依次选出每一个元素，作为排列的第一个元素，然后对剩余的元素进行全排列，
    //如此递归处理，从而得到所有元素的全排列。以对字符串abc进行全排列为例，我们可以这么做：以abc为例
    //固定a，求后面bc的排列：abc，acb
    //固定b，求后面ac的排列：bac，bca
    //固定c，求后面ba的排列：cba，cab
    public void recursion(int index) {
        if (index == arr.length - 1) {
            set.add(new String(arr));// 一种排列组合情况
            return;
        }
        for (int i = index; i < arr.length; i++) {
            swap(index, i);
            recursion(index + 1);// index+1，递归能“动”起来
            swap(i, index);
        }
    }

    private void swap(int index, int i) {
        if (index != i) {
            char tmp = arr[index];
            arr[index] = arr[i];
            arr[i] = tmp;
        }
    }
}