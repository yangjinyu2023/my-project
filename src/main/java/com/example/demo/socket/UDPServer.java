package com.example.demo.socket;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServer {
    public static void main(String[] args) throws Exception {
        // 服务端接收客户端发送的数据报
        try (DatagramSocket socket = new DatagramSocket(65001)) {
            byte[] buff = new byte[100];
            DatagramPacket packet = new DatagramPacket(buff, buff.length);
            // 接收客户端发送过来的内容，并将内容封装进DatagramPacket对象中
            socket.receive(packet);

            // 读取内容
            byte[] data = packet.getData();
            String content = new String(data, 0, data.length);
            System.out.println(content);
            // 得到长度
            byte[] sendedContent = String.valueOf(content.length()).getBytes();

            // 封装新的数据报，从原数据报对象中获取ip和端口
            DatagramPacket packetToClient = new DatagramPacket(sendedContent,
                    sendedContent.length, packet.getAddress(), packet.getPort());
            // 发送给客户端
            socket.send(packetToClient);
        }
    }
}