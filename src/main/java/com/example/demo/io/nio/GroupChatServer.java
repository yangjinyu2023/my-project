package com.example.demo.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

//https://blog.csdn.net/qq_43327091/article/details/106158787
public class GroupChatServer {

    private Selector selector;
    private ServerSocketChannel serverSocketChannel;
    private static final int PORT = 6666;

    public GroupChatServer() {
        init();
    }

    public static void main(String[] args) {
        GroupChatServer chatServer = new GroupChatServer();
        chatServer.listen();
    }

    /**
     * 监听
     */
    public void listen() {
        try {
            while (true) {
                int count = selector.select(2000);
                if (count > 0) {
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();

                    Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    while (iterator.hasNext()) {
                        SelectionKey selectionKey = iterator.next();
                        if (selectionKey.isAcceptable()) {
                            SocketChannel socketChannel = serverSocketChannel.accept();
                            System.out.println("监听到的 socketChannel:" + socketChannel.hashCode());
                            socketChannel.configureBlocking(false);
                            socketChannel.register(selector, SelectionKey.OP_READ);
                            System.out.println(socketChannel.getRemoteAddress() + " 上线...");
                        }
                        if (selectionKey.isReadable()) {
                            readClientData(selectionKey);
                        }

                        iterator.remove();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取客户端发送来数据
     */
    private void readClientData(SelectionKey selectionKey) {
        SocketChannel channel = null;
        try {
            channel = (SocketChannel) selectionKey.channel();
            System.out.println("接收到client SocketChannel:" + channel.hashCode());
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int count = channel.read(buffer);
            if (count > 0) {
                String msg = new String(buffer.array());
                System.out.println(msg + " from " + channel.getRemoteAddress());
                // 服务器向其他客户端转发消息
                forwardOtherClient(msg, channel);
            }
        } catch (IOException e) {
            try {
                System.out.println(channel.getRemoteAddress() + " 下线..");
                // 取消注册,并关闭通道
                selectionKey.cancel();
                channel.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * 向其他客户端发送消息并排除自己
     */
    private void forwardOtherClient(String message, SocketChannel self) {
        try {
            for (SelectionKey key : selector.keys()) {
                Channel channel = key.channel();
                if (channel instanceof SocketChannel && channel != self) {
                    SocketChannel dest = (SocketChannel) channel;
                    dest.write(ByteBuffer.wrap(message.getBytes()));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 初始化
     */
    private void init() {
        try {
            selector = Selector.open();
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.socket().bind(new InetSocketAddress(PORT));
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

