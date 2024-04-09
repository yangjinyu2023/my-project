package com.example.demo.jvm.reflect;

import com.example.demo.domain.Robot;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @description:
 * @author: yangjinyu
 * @time: 2020/1/18 14:06
 */

public class ReflectDemo {

    public static void main(String[] args) throws Exception {
        // 获取类类型对象
        Class clazz = Class.forName("com.example.demo.domain.Robot");
        // 获取类对象
        Robot robot = (Robot) clazz.newInstance();

        // getDeclaredMethod 可以获取该类所有方法
        // 不能获取该类继承的方法，不能获取该类所实现的接口的方法
        Method whisperings = clazz.getDeclaredMethod("whisperings", String.class);
        // 执行私有方法，要加setAccessible(true)，否则报IllegalAccess
        whisperings.setAccessible(true);
        Object result1 = whisperings.invoke(robot, "hello");
        System.out.println(result1.toString());

        // getMethod只能获取public方法，否则报NoSuchMethod
        // 包括该类继承的方法，该类所实现的接口的方法
        Method say = clazz.getMethod("say", String.class, int.class);
        Object result2 = say.invoke(clazz.newInstance(), "hello", 2);
        System.out.println(result2.toString());

        // 获取字段
        Field name = clazz.getDeclaredField("name");
        name.setAccessible(true);
        name.set(robot, "robot");
        System.out.println(robot.getName());
    }
}