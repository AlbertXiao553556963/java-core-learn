package com.xmx.network.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;

/**
 * construct server with ServerSocketChannel and selector
 *
 * @author: XiaoMingxuan
 * @email: mingxuan.xmx@alibaba-inc.com
 * @create: 2019-01-23 21:23
 **/
public class NioServerV2 {

    private static void registerRead(Selector selector, SelectionKey selectionKey) {

        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.attachment();
        try {
            // non blocking
            SocketChannel socketChannel = serverSocketChannel.accept();
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ, socketChannel);
            System.out.println("收到新连接 : " + socketChannel.getRemoteAddress());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readAndResponse(SelectionKey selectionKey) throws IOException {
        SocketChannel socketChannel = (SocketChannel) selectionKey.attachment();
        try {
            ByteBuffer requestBuffer = ByteBuffer.allocate(1024);
            while (socketChannel.isOpen() && socketChannel.read(requestBuffer) != -1) {
                // 长连接情况下,需要手动判断数据有没有读取结束 (此处做一个简单的判断: 超过0字节就认为请求结束了)
                if (requestBuffer.position() > 0) break;
            }

            // 如果发送的数据为0,取消订阅
            if (requestBuffer.position() == 0) {
                selectionKey.cancel(); // 取消事件订阅
                return;
            }

            requestBuffer.flip();
            byte[] content = new byte[requestBuffer.limit()];
            requestBuffer.get(content);
            System.out.println("收到数据" + new String(content) + " 来自：" + socketChannel.getRemoteAddress());

            String response = "Hello World";
            ByteBuffer buffer = ByteBuffer.wrap(response.getBytes());
            while (buffer.hasRemaining()) {
                socketChannel.write(buffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("链接异常" + socketChannel.getRemoteAddress());
            selectionKey.cancel(); // 取消事件订阅
        }
    }

    public static void main(String[] args) throws IOException {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);

        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT, serverSocketChannel);

        serverSocketChannel.socket().bind(new InetSocketAddress(8080));

        System.out.println("server start");

        while (true) {
            // 不再轮询通道,改用下面轮询事件的方式.select方法有阻塞效果
            // 直到有注册感兴趣的事件通知才会返回
            selector.select();

            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            selectionKeys.removeIf(selectionKey -> {

                if (selectionKey.isAcceptable()) {
                    registerRead(selector, selectionKey);
                }

                if (selectionKey.isReadable()) {
                    try {
                        readAndResponse(selectionKey);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                return true;
            });

            selector.selectNow();
        }
    }


}
