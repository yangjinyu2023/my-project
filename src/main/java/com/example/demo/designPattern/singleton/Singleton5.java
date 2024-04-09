package com.example.demo.designPattern.singleton;

/**
 * @description: 枚举方式（是单例模式的最佳实现方式）
 * @author: yangjinyu
 * @time: 2020/1/30 15:49
 */
public enum Singleton5 {
    // 第一次调用时初始化
    // 枚举的初始化通过static代码块来进行初始化，
    // 而static代码块是绝对线程安全的，只能由JVM来调度，这样保证了线程安全。
    INSTNACE;

    // 枚举类
    // 1、单例可以通过私有构造函数防止破坏单例，
    // 但是还是能通过反射实例化（拿到declaredConstructor，setAccessible为true）；
    // Constructor.newInstance限制了不能是枚举类型
    // 2、如果实现了Serializable接口，能通过反序列化破坏单例；
    // 反序列化时对枚举类型做了处理，返回原INSTANCE
    // 3、如果实现了Cloneable接口，能通过clone方法破坏单例；
    // 枚举类的clone方法直接抛NotSupported异常，并且是final的，不允许被重写
    public void say() {
        System.out.println("hello");
    }
}