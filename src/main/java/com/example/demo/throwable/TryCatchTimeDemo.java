package com.example.demo.throwable;

/**
 * @description: try-catch性能
 * @author: yangjinyu
 * @time: 2020/2/2 16:59
 */
public class TryCatchTimeDemo {
    public static void testException(String[] array) {
        try {
            System.out.println(array[0]);
        } catch (Exception e) {
            System.out.println("array cannot be null");
        }
    }

    public static void testIf(String[] array) {
        if (array != null) {
            System.out.println(array[0]);
        } else {
            System.out.println("array cannot be null");
        }
    }

    public static void main(String[] args) {
        long start = System.nanoTime();// 纳秒级别
        //testException(null);// 400000
        testIf(null);// 330000
        System.out.println("cost " + (System.nanoTime() - start));
    }
}