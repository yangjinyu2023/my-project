package com.example.demo.throwable;

import lombok.extern.slf4j.Slf4j;

/**
 * @description:
 * @author: yangjinyu
 * @time: 2020/2/2 15:50
 */

public class ThrowDemo {

    // 查看异常的捕获与抛出
    public static void main(String[] args) {
        ThrowDemo demo = new ThrowDemo();
        try {
            // doWork未catch的异常，会抛到上一层
            demo.doWork();
        } catch (NullPointerException e) {
            System.out.println("NullPointerException: " + e.toString());
        }

    }

    public void doWork() {
        try {
            ThrowDemo demo = new ThrowDemo();
            //demo.throwArithmetic();
            demo.throwNullPointer();
        } catch (ArithmeticException ae) {
            System.out.println("ArithmeticException: " + ae.toString());
        } finally {
            System.out.println("finally");
        }
    }

    public void throwArithmetic() {
        double d = 10 / 0;
    }

    public void throwNullPointer() {
        String s = null;
        s.toString();
    }
}