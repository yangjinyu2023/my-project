package com.example.demo.io;

import java.io.*;

/**
 * 使用FileReader和FileWriter实现文本复制
 *
 * @author yangjinyu
 * @time 2022/6/21 20:11
 */
public class CopyFile {
    public static void main(String[] args) throws IOException {
        File f1 = new File("F:\\file1.txt");
        File f2 = new File("F:\\file2.txt");
        FileReader fileReader = new FileReader(f1);
        FileWriter fileWriter = new FileWriter(f2);
        // 使用缓冲流提高性能
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        //copyByChar(fileReader, fileWriter);
        //copyByCharArr(fileReader, fileWriter);
        copyByCharArr(bufferedReader, bufferedWriter);
        fileReader.close();
        fileWriter.close();
        bufferedReader.close();
        bufferedWriter.close();
    }

    private static void copyByChar(FileReader fileReader, FileWriter fileWriter) throws IOException {
        int ch;
        while ((ch = fileReader.read()) != -1) {
            fileWriter.write(ch);
        }
        fileWriter.flush();
    }

    private static void copyByCharArr(FileReader fileReader, FileWriter fileWriter) throws IOException {
        char[] chars = new char[1024];
        int ch;
        while ((ch = fileReader.read(chars)) != -1) {
            fileWriter.write(chars, 0, ch);
        }
        fileWriter.flush();
    }

    private static void copyByCharArr(BufferedReader fileReader, BufferedWriter fileWriter) throws IOException {
        char[] chars = new char[1024];
        int ch;
        while ((ch = fileReader.read(chars)) != -1) {
            fileWriter.write(chars, 0, ch);
        }
        fileWriter.flush();
    }
}