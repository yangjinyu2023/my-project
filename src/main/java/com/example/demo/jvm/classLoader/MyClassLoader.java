package com.example.demo.jvm.classLoader;

import java.io.*;
import java.nio.file.Files;

public class MyClassLoader extends ClassLoader {
    private String path;
    private String classLoaderName;

    public MyClassLoader(String path, String classLoaderName) {
        this.path = path;
        this.classLoaderName = classLoaderName;
    }

    // 寻找类文件
    @Override
    public Class findClass(String name) {
        // 自定义classLoader的用途
        // 1.可找到指定位置的二进制文件，不受classpath限制
        // 2.如果字节码文件被加密，可以在这里解密
        // 3.可以修改字节码文件，添加信息
        byte[] bytes = loadClassData(name);
        return defineClass(name, bytes, 0, bytes.length);
    }

    // 加载类文件
    private byte[] loadClassData(String name) {
        String fileName = path + name + ".class";
        InputStream inputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            inputStream = Files.newInputStream(new File(fileName).toPath());
            byteArrayOutputStream = new ByteArrayOutputStream();
            int i;
            while ((i = inputStream.read()) != -1) {
                byteArrayOutputStream.write(i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
                byteArrayOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return byteArrayOutputStream.toByteArray();
    }
}