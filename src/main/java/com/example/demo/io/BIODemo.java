package com.example.demo.io;

import java.io.*;

public class BIODemo {
    public static void main(String[] args) {
        BIODemo demo = new BIODemo();
        try {
            // demo.writeToFile();
            demo.readFromFile();
            //demo.printToFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void writeToFile() throws IOException {
        InputStream in = System.in;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
        FileWriter fileWriter = new FileWriter("C:/Users/Administrator/Desktop/test.txt");
        try {
            // byte[] bytes = new byte[1024];
            char[] chars = new char[1024];
            int len;
            while ((len = bufferedReader.read(chars)) != -1) {
                // fileWriter.write(new String(bytes, 0, len));
                fileWriter.write(chars, 0, len);
                fileWriter.flush();
            }
        } finally {
            try {
                bufferedReader.close();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void readFromFile() throws IOException {
        OutputStream out = System.out;
        BufferedReader bufferedReader = new BufferedReader(new FileReader("C:/Users/Administrator/Desktop/test.txt"));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(out));// 从System.out读的是字节
        try {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
//               out.write(line.getBytes());
//               out.write("\r\n".getBytes());
                bufferedWriter.write(line);
                bufferedWriter.newLine();
                bufferedWriter.flush();
                //System.out.println(line);
            }
        } finally {
            try {
                bufferedReader.close();
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void printToFile() throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter("C:/Users/Administrator/Desktop/test.txt");
        printWriter.write("write");
        printWriter.println("println");
        printWriter.println("println");
        printWriter.close();
    }
}