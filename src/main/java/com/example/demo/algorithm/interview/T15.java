package com.example.demo.algorithm.interview;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 京东物流-终端
 */
public class T15 {

    /**
     * 【（【】【】）】，{{}【】}，【【】（）{}】，以上三种情况均为正确顺序和匹配方式，
     * 包括左括号和右括号个数匹配，先左括号后有括号的顺序，
     * 请实现一个检查算法，入参是字符串，出参是布尔值。
     */
    static class S1 {
        public static void main(String[] args) {
            System.out.println(new S1().check("【（【】【】）】"));
            System.out.println(new S1().check("{{}【】}"));
            System.out.println(new S1().check("【【】（）{}】"));
            System.out.println(new S1().check("【【】（{}】"));
            System.out.println(new S1().check("【】（）{}】"));
        }

        public boolean check(String str) {
            if (str == null || str.length() == 0) {
                return false;
            }
            char[] arr = str.toCharArray();
            Stack<Character> stack = new Stack<>();
            List<Character> needPush = Arrays.asList('{', '【', '（');
            for (char c : arr) {
                if (needPush.contains(c)) {
                    stack.push(c);
                } else {
                    // 这个判断很重要
                    if (stack.isEmpty()) {
                        return false;
                    }
                    Character left = stack.pop();
                    if (!isMatch(left, c)) {
                        return false;
                    }
                }
            }
            return true;
        }

        private boolean isMatch(char c, char ch) {
            if (ch == '）') {
                return c == '（';
            }
            if (ch == '】') {
                return c == '【';
            }
            if (ch == '}') {
                return c == '{';
            }
            return false;
        }
    }

    /**
     * 压缩字符串：由若干对字符+数字组成。每对组合中的数字表示为该字符在此处出现的次数。
     * 例如："a1b2c3" 表示的原字符串为 "abbccc"。
     */
    static class S2 {
        public static void main(String[] args) {
            System.out.println(new S2().compressString("abbccc"));
        }
        public String compressString(String input) {
            StringBuilder compressed = new StringBuilder();
            int count = 1;
            for (int i = 0; i < input.length(); i++) {
                if (i + 1 < input.length() && input.charAt(i) == input.charAt(i + 1)) {
                    count++;
                } else {
                    compressed.append(input.charAt(i));
                    compressed.append(count);
                    count = 1;
                }
            }
            return compressed.toString();
        }
    }

    /**
     * 一个sql求出一班平均分及格同学个数和二班平均分不及格同学个数。
     */
    static class S3 {
        //此题注意，SUM和AVG不能嵌套使用，所以先AVG，在用SUM和CASE-WHEN得到想要的结果
        //select sum(score),avg(score) from tms_ns.student_score group by class,name; --ok
        //select sum(avg(score)) from tms_ns.student_score group by class,name; -- [HY000]: Invalid use of group function

        //SELECT
        //    SUM(CASE
        //            WHEN avg_score < 60 AND class = '一班' THEN 1
        //            ELSE 0
        //        END) AS class1_success_count,
        //    SUM(CASE
        //            WHEN avg_score >= 60 AND class = '二班' THEN 1
        //            ELSE 0
        //        END) AS class2_fail_count
        //FROM
        //    (SELECT
        //        class,
        //        name,
        //        AVG(score) as avg_score
        //    FROM
        //        tms_ns.student_score
        //    GROUP BY
        //        class,
        //        name) t
    }
}