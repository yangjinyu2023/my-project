package com.example.demo.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author yangjinyu
 * @time 2021/5/25 15:49
 */
public class EpollClient3 {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("127.0.0.1", 8000));

        ByteBuffer writeBuffer = ByteBuffer.allocate(32);
        ByteBuffer readBuffer = ByteBuffer.allocate(32);

        writeBuffer.put("hello, i'm client3".getBytes());
        writeBuffer.flip();

        while (true) {
            writeBuffer.rewind();
            socketChannel.write(writeBuffer);
            readBuffer.clear();
            socketChannel.read(readBuffer);
            System.out.println("server : " + new String(readBuffer.array()));
        }
    }
}