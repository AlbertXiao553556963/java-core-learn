package com.xmx.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author: XiaoMingxuan
 * @email: mingxuan.xmx@alibaba-inc.com
 * @create: 2018-12-09 19:47
 **/
public class TestSocket {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket1 = new ServerSocket(6379);
        System.out.println("监听端口6379，程序1启动");
        Socket socket = serverSocket1.accept();
        byte[] bytes = new byte[1024];
        socket.getInputStream().read(bytes);
        System.out.println(bytes.toString());
        System.out.println(new String(bytes));

    }
}
