package com.example.demo.test;

import com.example.demo.utils.DateUtil;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class TestString {

    public static void m1() {
        String a = "a1";
        String b = "a" + 1;
        System.out.println(a == b);//false
    }

    public static void m2() {
        String a = "ab";
        String bb = "b";
        String b = "a" + bb;
        System.out.println(a == b);//false
    }

    public static void m3() {
        String a = "ab";
        final String bb = "b";
        String b = "a" + bb;
        System.out.println(a == b);//false
    }

    public static void m4() {
        String a = "ab";
        final String bb = getBB();
        String b = "a" + bb;
        System.out.println(a == b);//false
    }

    private static String getBB() {
        return "b";
    }


    private static String a = "ab";

    public static void m5() {
        String s1 = "a";
        String s2 = "b";
        String s = s1 + s2;
        System.out.println(s == a);//false
        System.out.println(s.intern() == a);//true
    }

    private static String ab = new String("ab");

    public static void m6() {
        String s1 = "a";
        String s2 = "b";
        String s = s1 + s2;
        System.out.println(s == ab);//false
        System.out.println(s.intern() == ab);//false
        System.out.println(s.intern() == ab.intern());//true
    }

    private static void m7() {
        String s1 = "a";
        String s2 = new String("a");
        s2.intern();
        System.out.println(s1 == s2);//false
        s2 = s2.intern();
        System.out.println(s1 == s2);//true
    }

    private static void m8() {
        Integer a = 127;
        Integer b = 127;
        System.out.println(a == b);//true
        Integer c = 128;
        Integer d = 128;
        System.out.println(c == d);//false
    }

    public static void main(String[] args) {
        //m1();
        //m2();
        //m3();
        //m4();
        //m5();
        //m6();
        //m7();
        //m8();
        m9();
    }

    private static void m9() {
        List<Integer> yearMonths = Arrays.asList(202201, 202206, 202112, 202111, 202107, 202205);
        List<Integer> sortedList = yearMonths.stream().sorted().collect(Collectors.toList());
        List<List<Integer>> result = new ArrayList<>();
        if (!CollectionUtils.isEmpty(sortedList)) {
            Stack<Integer> stack = new Stack<>();
            // 连续入栈，不连续先将栈中元素全部出栈，再继续入栈
            for (Integer ym : sortedList) {
                if (stack.isEmpty()) {
                    stack.push(ym);
                } else {
                    if (DateUtil.addMonths(ym, -1) == stack.peek().intValue()) {
                        stack.push(ym);
                    } else {
                        result.add(Arrays.asList(stack.get(0), stack.peek()));
                        stack.clear();
                        stack.push(ym);
                    }
                }
            }
            if (!stack.isEmpty()) {
                result.add(Arrays.asList(stack.get(0), stack.peek()));
            }
            System.out.println(result);
        }
    }
}
