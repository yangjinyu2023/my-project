package com.example.demo.socket;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {
    public static void main(String[] args) throws Exception {
        DatagramPacket receivedPacket;
        try (DatagramSocket socket = new DatagramSocket()) {
            byte[] buf = "yangjinyu".getBytes();
            DatagramPacket packet = new DatagramPacket(buf, buf.length,
                    InetAddress.getByName("127.0.0.1"), 65001);
            socket.send(packet);

            byte[] data = new byte[100];
            receivedPacket = new DatagramPacket(data, data.length);
            socket.receive(receivedPacket);
        }// 这里不要再指定端口
        String content = new String(receivedPacket.getData(), 0, receivedPacket.getLength());
        System.out.println(content);
    }
}