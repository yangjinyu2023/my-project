package com.example.demo.utils;

import java.io.*;

/**
 * @author yangjinyu
 * @time 2021/5/19 11:29
 */
public class SerialCloneUtil {
    @SuppressWarnings("unchecked")
    public static <T extends Serializable> T deepClone(T t) throws CloneNotSupportedException {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(t);

            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
            ObjectInputStream inputStream = new ObjectInputStream(byteArrayInputStream);
            return (T) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            CloneNotSupportedException cloneNotSupportedException = new CloneNotSupportedException();
            e.initCause(cloneNotSupportedException);
            throw cloneNotSupportedException;
        }
    }
}