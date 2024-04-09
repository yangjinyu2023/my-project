package com.example.demo.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description:
 * @author: yangjinyu
 * @time: 2020/2/4 14:01
 */
public class BIOPlainEchoServer {
    public void server(int port) throws IOException {
        // 将ServerSocket绑定到指定的端口
        final ServerSocket socket = new ServerSocket(port);
        ExecutorService executorService = Executors.newFixedThreadPool(6);
        while (true) {
            // 阻塞直到收到新的客户端连接
            final Socket clientSocket = socket.accept();
            System.out.println("Accepted connection from " + clientSocket);
            executorService.execute(() -> {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                    PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
                    while (true) {
                        // 从客户端读取数据并写回
                        writer.println(reader.readLine());
                        writer.flush();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}