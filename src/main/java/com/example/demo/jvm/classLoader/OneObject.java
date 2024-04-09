package com.example.demo.jvm.classLoader;

/**
 * 测试final的实例变量何时初始化
 */
public class OneObject {

    public final int a = geta();

    public int b = getb();

    private int getb() {
        System.out.println("非final实例变量初始化");
        return 1;
    }

    private int geta() {
        System.out.println("final实例变量初始化");
        return 1;
    }

    public OneObject(){
        System.out.println("构造函数");
    }

    public static int c;


    static {
        System.out.println("静态变量初始化");
        c = 1;
    }

    public static int getc() {
        return 1;
    }
}