package com.example.demo.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TCPClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 65000);
        OutputStream outputStream = socket.getOutputStream();
        InputStream inputStream = socket.getInputStream();
        try {
            outputStream.write("yangjinyu".getBytes());
            byte[] buff = new byte[1024];
            int len = inputStream.read(buff);
            String content = new String(buff, 0, len);
            System.out.println(content);
        }finally {
            inputStream.close();
            outputStream.close();
            socket.close();
        }
    }
}