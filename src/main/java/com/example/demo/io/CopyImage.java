package com.example.demo.io;

import java.io.*;

/**
 * 使用FileReader和FileWriter实现文本复制
 *
 * @author yangjinyu
 * @time 2022/6/21 20:11
 */
public class CopyImage {
    public static void main(String[] args) throws IOException {
        File f1 = new File("F:\\thZ83TUB8U.jpg");
        File f2 = new File("F:\\thZ83TUB8U_copy.jpg");
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(f1));
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(f2));
        byte[] chars = new byte[1024];
        int ch;
        while ((ch = bufferedInputStream.read(chars)) != -1) {
            bufferedOutputStream.write(chars, 0, ch);
        }
        bufferedInputStream.close();
        bufferedOutputStream.flush();
    }
}