package com.xmx.network.bio;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * create thread responding every request that`s cost too much resource
 *
 * @author: XiaoMingxuan
 * @email: mingxuan.xmx@alibaba-inc.com
 * @create: 2019-01-23 17:43
 **/
public class BioServer {

    private static ThreadPoolExecutor threadPool = new ThreadPoolExecutor(10, 10,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>());

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("server startup");

        while (!serverSocket.isClosed()) {
            Socket socket = serverSocket.accept();
            System.out.println("receive new connection:" + socket);
            threadPool.execute(() -> {
                try {
                    InputStream inputStream = socket.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
                    String msg;
                    while (true) {
                        msg = bufferedReader.readLine();
                        if (msg == null || msg.length() == 0) {
                            continue;
                        }
                        System.out.println(msg);
                        OutputStream outputStream = socket.getOutputStream();
                        outputStream.write(("hello this is server \n").getBytes());
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        serverSocket.close();
    }
}
