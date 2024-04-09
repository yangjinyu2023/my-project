package com.example.demo.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class LengthCalculator extends Thread {
    private Socket socket;

    public LengthCalculator(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            // 获取socket输出流
            OutputStream outputStream = socket.getOutputStream();
            // 获取socket输入流
            InputStream inputStream = socket.getInputStream();
            // buff主要用于读取输入的内容，存成byte数组，ch主要用来获取读取数组的长度
            byte[] buff = new byte[1024];
            int len = inputStream.read(buff);
            // 将接收流的byte数组转换成字符串，这里获取的内容是客户端发送过来的参数串参数
            String content = new String(buff, 0, len);
            System.out.println(content);
            // 往输出流里写入获得的字符串的长度，回发给客户端
            outputStream.write(String.valueOf(content.length()).getBytes());
            // 关闭输入输出流和socket
            inputStream.close();
            outputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}