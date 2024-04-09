package com.example.demo.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author yangjinyu
 * @time 2021/5/25 15:40
 */
public class EpollServer {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.socket().bind(new InetSocketAddress("127.0.0.1", 8000));
        ssc.configureBlocking(false);

        Selector selector = Selector.open();
        // 注册 channel，并且指定感兴趣的事件是 Accept
        ssc.register(selector, SelectionKey.OP_ACCEPT);

        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
        ByteBuffer writeBuffer = ByteBuffer.allocate(128);
        writeBuffer.put("copy".getBytes());
        writeBuffer.flip();

        while (true) {
            // 使用 select 方法阻塞住线程，当select 返回的时候，线程被唤醒。
            selector.select();
            // 再通过selectedKeys方法得到所有可用channel的集合。
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> it = keys.iterator();

            // 遍历这个集合，如果其中channel 上有连接到达，就接受新的连接，然后把这个新的连接也注册到selector中去。
            while (it.hasNext()) {
                SelectionKey key = it.next();
                it.remove();
                // 如果有channel是读，那就把数据读出来，并且把它感兴趣的事件改成写。
                // 如果是写，就把数据写出去，并且把感兴趣的事件改成读。
                if (key.isAcceptable()) {
                    // 创建新的连接，并且把连接注册到selector上，而且，声明这个channel只对读操作感兴趣。
                    SocketChannel socketChannel = ssc.accept();
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ);
                } else if (key.isReadable()) {
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    readBuffer.clear();
                    socketChannel.read(readBuffer);
                    readBuffer.flip();
                    System.out.println("client : " + new String(readBuffer.array()));
                    key.interestOps(SelectionKey.OP_WRITE);
                } else if (key.isWritable()) {
                    writeBuffer.rewind();
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    socketChannel.write(writeBuffer);
                    key.interestOps(SelectionKey.OP_READ);
                }
            }
        }
    }
}