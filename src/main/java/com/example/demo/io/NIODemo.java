package com.example.demo.io;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIODemo {
    public static void main(String[] args) {
        try {
            read();
            transferChannel();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void read() throws IOException {
        RandomAccessFile accessFile = new RandomAccessFile("H:\\data.txt", "rw");
        FileChannel inChannel = accessFile.getChannel();
        ByteBuffer buf = ByteBuffer.allocate(48);
        String author = "author yjy ";
        buf.put(author.getBytes());
        int readBytes = inChannel.read(buf);
        // 一次读取48字节，直到读完为止
        while (readBytes != -1) {
            System.out.println("Read" + readBytes);
            buf.flip();
            while (buf.hasRemaining()) {
                System.out.println((char) buf.get());
            }
            buf.clear();
            readBytes = inChannel.read(buf);
        }
        inChannel.close();
        accessFile.close();
    }

    private static void transferChannel() throws IOException {
        RandomAccessFile fromFile = new RandomAccessFile("H:\\data.txt", "rw");
        FileChannel fromChannel = fromFile.getChannel();

        RandomAccessFile toFile = new RandomAccessFile("H:\\data2.txt", "rw");
        FileChannel toChannel = toFile.getChannel();

        long position = 0;
        long count = fromChannel.size();

        toChannel.transferFrom(fromChannel, position, count);
        fromFile.close();
        fromChannel.close();
        toFile.close();
        toChannel.close();
    }
}