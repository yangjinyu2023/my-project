package com.example.demo;

/**
 * @author yangjinyu
 * @time 2021/5/20 17:01
 */

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
        System.out.println(a==b);
        Integer c = 128;
        Integer d = 128;
        System.out.println(c==d);
    }

    public static void main(String[] args) {
//        m1();
//        m2();
//        m3();
//        m4();
//        m5();
//        m6();
//        m7();
        m8();
        System.out.println(0%3);
        System.out.println(1%3);
        System.out.println(2%3);
        System.out.println(3%3);
        System.out.println(4%3);

    }
}
