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
            System.out.println("�յ������� : " + socketChannel.getRemoteAddress());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readAndResponse(SelectionKey selectionKey) throws IOException {
        SocketChannel socketChannel = (SocketChannel) selectionKey.attachment();
        try {
            ByteBuffer requestBuffer = ByteBuffer.allocate(1024);
            while (socketChannel.isOpen() && socketChannel.read(requestBuffer) != -1) {
                // �����������,��Ҫ�ֶ��ж�������û�ж�ȡ���� (�˴���һ���򵥵��ж�: ����0�ֽھ���Ϊ���������)
                if (requestBuffer.position() > 0) break;
            }

            // ������͵�����Ϊ0,ȡ������
            if (requestBuffer.position() == 0) {
                selectionKey.cancel(); // ȡ���¼�����
                return;
            }

            requestBuffer.flip();
            byte[] content = new byte[requestBuffer.limit()];
            requestBuffer.get(content);
            System.out.println("�յ�����" + new String(content) + " ���ԣ�" + socketChannel.getRemoteAddress());

            String response = "Hello World";
            ByteBuffer buffer = ByteBuffer.wrap(response.getBytes());
            while (buffer.hasRemaining()) {
                socketChannel.write(buffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("�����쳣" + socketChannel.getRemoteAddress());
            selectionKey.cancel(); // ȡ���¼�����
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
            // ������ѯͨ��,����������ѯ�¼��ķ�ʽ.select����������Ч��
            // ֱ����ע�����Ȥ���¼�֪ͨ�Ż᷵��
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
