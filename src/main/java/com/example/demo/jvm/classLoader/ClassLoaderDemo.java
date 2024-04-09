package com.example.demo.jvm.classLoader;

/**
 * @description:
 * @author: yangjinyu
 * @time: 2020/1/18 16:25
 */
public class ClassLoaderDemo {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        System.out.println(System.getProperty("java.class.path"));
        String path = "F:/demo/src/main/external/";
        MyClassLoader myClassLoader = new MyClassLoader(path, "myClassLoader");

        Class clazz = myClassLoader.findClass("Wail");
        System.out.println(clazz.getClassLoader().toString());
        clazz.newInstance();
    }
}