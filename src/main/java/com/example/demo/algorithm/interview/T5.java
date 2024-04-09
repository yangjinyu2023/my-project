package com.example.demo.algorithm.interview;

/**
 * 手写单例模式
 *
 * @author yangjinyu
 * @time 2022/10/19 16:25
 */
public class T5 {
    // 私有化构造函数，只允许通过getInstance获取实例
    private T5() {
    }

    // 饿汉式
    private final static T5 INSTANCE1 = new T5();

    public static T5 getInstance1() {
        return INSTANCE1;
    }

    // 懒汉，双重检测
    private static volatile T5 INSTANCE2;

    public static T5 getInstance2() {
        if (INSTANCE2 == null) {
            synchronized (T5.class) {
                if (INSTANCE2 == null) {
                    INSTANCE2 = new T5();
                }
            }
        }
        return INSTANCE2;
    }

    // 内部类，获取内部类的类变量时才被加载
    static class Holder {
        private final static T5 INSTANCE3 = new T5();
    }

    public static T5 getInstance3() {
        return Holder.INSTANCE3;
    }

    // 枚举类
    // 1、单例可以通过私有构造函数防止破坏单例，
    // 但是还是能通过反射实例化（拿到declaredConstructor，setAccessible为true）；
    // Constructor.newInstance限制了不能是枚举类型
    // 2、如果实现了Serializable接口，能通过反序列化破坏单例；
    // 反序列化时对枚举类型做了处理，返回原INSTANCE
    // 3、如果实现了Cloneable接口，能通过clone方法破坏单例；
    // 枚举类的clone方法直接抛NotSupported异常，并且是final的，不允许被重写
    enum T {
        INSTANCE
    }
}