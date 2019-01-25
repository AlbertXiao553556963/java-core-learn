package com.xmx.network.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 * @author: XiaoMingxuan
 * @email: mingxuan.xmx@alibaba-inc.com
 * @create: 2019-01-24 19:00
 **/
public class NioClient {

    private static String input() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("please input:");
        String msg = scanner.nextLine();
        return msg;
    }

    private static String read(SocketChannel socketChannel) throws IOException {
        ByteBuffer responseBuffer = ByteBuffer.allocateDirect(1024);

        while (socketChannel.isOpen() && socketChannel.read(responseBuffer) != -1) {
            if (responseBuffer.position() > 0) break;
        }

        responseBuffer.flip();
        byte[] content = new byte[responseBuffer.limit()];
        responseBuffer.get(content);
        return new String(content);
    }

    private static void write(String msg, SocketChannel socketChannel) throws IOException {
        ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
        while (buffer.hasRemaining()) {
            socketChannel.write(buffer);
        }
    }

    public static void main(String[] args) throws IOException {

        while (true) {

            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.connect(new InetSocketAddress("127.0.0.1", 8080));

            while (!socketChannel.finishConnect()) {
                Thread.yield();
            }

            String msg = input();

            write(msg, socketChannel);

            System.out.println(read(socketChannel));

            socketChannel.close();
        }

    }


}
