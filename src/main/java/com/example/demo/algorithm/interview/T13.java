package com.example.demo.algorithm.interview;

/**
 * 给定两个string字符串（不等长），全部为数字，首位不为0，求和 臻盛网络面试题
 *
 * @author yangjinyu
 * @time 2022/9/3 16:14
 */
public class T13 {
    public static void main(String[] args) {
        System.out.println(new T13().add_new("4999999", "99"));
        System.out.println(new T13().add("999", "2"));
    }

    // 关键点1：前面加0补齐长度，便于运算
    // 关键点2：从低位到高位进行相加，大于等于10进位
    // 关键点3：最后反转字符串
    public String add(String s1, String s2) {
        if (s1 == null || s1.length() == 0) {
            return s2;
        }
        if (s2 == null || s2.length() == 0) {
            return s1;
        }
        String l = s1.length() > s2.length() ? s1 : s2;
        String s = s1.length() <= s2.length() ? s1 : s2;
        int length = Math.max(s1.length(), s2.length());
        StringBuilder pad = new StringBuilder();
        for (int i = 0; i < length - s.length(); i++) {
            pad.append("0");
        }
        s = pad + s;
        StringBuilder res = new StringBuilder();
        int rest = 0;
        for (int i = length - 1; i >= 0; i--) {
            int sum = (l.charAt(i) - '0') + (s.charAt(i) - '0') + rest;
            if (sum >= 10) {
                res.append(sum % 10);
                rest = 1;
            }
            else {
                res.append(sum);
                rest = 0;
            }
        }
        if (rest == 1) {
            res.append("1");
        }
        return res.reverse().toString();
    }

    public String add_new(String s1, String s2) {
        if (s1 == null || "".equals(s1)) {
            return s2;
        }
        if (s2 == null || "".equals(s2)) {
            return s1;
        }
        int l1 = s1.length() - 1;
        int l2 = s2.length() - 1;
        int bit = 0;
        StringBuilder s = new StringBuilder();
        while (l1 >= 0 || l2 >= 0) {
            int c1 = 0, c2 = 0;
            if (l1 >= 0) {
                c1 = s1.charAt(l1) - '0';
                l1--;
            }
            if (l2 >= 0) {
                c2 = s2.charAt(l2) - '0';
                l2--;
            }
            int sum = c1 + c2 + bit;
            if (sum >= 10) {
                sum = sum % 10;
                bit = 1;
            }
            else {
                bit = 0;// 这步容易忘，要记住
            }
            s.append(sum);
        }
        if (bit > 0) {
            s.append(bit);
        }
        return s.reverse().toString();
    }
}