package com.xmx.network.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;

/**
 * construct server with ServerSocketChannel
 *
 * @author: XiaoMingxuan
 * @email: mingxuan.xmx@alibaba-inc.com
 * @create: 2019-01-23 21:23
 **/
public class NioServerV1 {

    private static ArrayList<SocketChannel> channels = new ArrayList<>();

    private static void addSocketChannel(SocketChannel socketChannel) throws IOException {
        System.out.println("accept connection from:" + socketChannel.getRemoteAddress());
        socketChannel.configureBlocking(false);
        channels.add(socketChannel);
    }

    private static boolean read(SocketChannel socketChannel) throws IOException {
        ByteBuffer requestBuffer = ByteBuffer.allocate(1024);
        if (socketChannel.read(requestBuffer) == 0) {
            // 等于0,代表这个通道没有数据需要处理,那就待会再处理
            return false;
        }
        while (socketChannel.isOpen() && socketChannel.read(requestBuffer) != -1) {
            // 长连接情况下,需要手动判断数据有没有读取结束 例如加一个标志位
            // (此处做一个简单的判断: 超过0字节就认为请求结束了)
            if (requestBuffer.position() > 0) break;
        }

        requestBuffer.flip();

        byte[] content = new byte[requestBuffer.limit()];
        requestBuffer.get(content);
        System.out.println("收到数据" + new String(content) + " 来自：" + socketChannel.getRemoteAddress());
        return true;
    }

    private static void write(SocketChannel socketChannel) throws IOException {
        String response = "Hello World";
        ByteBuffer buffer = ByteBuffer.wrap(response.getBytes());
        while (buffer.hasRemaining()) {
            socketChannel.write(buffer);
        }
    }

    private static void process() {
        channels.removeIf(socketChannel -> {
            try {
                if (read(socketChannel)) {
                    write(socketChannel);
                    return true;
                }
                return false;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        });
    }

    public static void main(String[] args) throws IOException {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(new InetSocketAddress(8080));
        System.out.println("server start");

        while (true) {
            // non blocking
            SocketChannel socketChannel = serverSocketChannel.accept();
            if (socketChannel != null) {
                addSocketChannel(socketChannel);
            } else {
                process();
            }
        }
    }


}
