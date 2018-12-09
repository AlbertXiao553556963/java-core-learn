package com.redis_client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author: XiaoMingxuan
 * @email: mingxuan.xmx@alibaba-inc.com
 * 模拟redis客户端发送指令的方式
 * @create: 2018-12-09 20:59
 **/
public class MyRedisClient {

    OutputStream outputStream;

    InputStream inputStream;

    public MyRedisClient(String host, int port) throws IOException {
        Socket socket = new Socket(host,port);
        outputStream = socket.getOutputStream();
        inputStream = socket.getInputStream();
    }

    public void set(String key , String value) throws IOException {
        StringBuffer data = new StringBuffer();
        data.append("*3").append("\r\n");

        data.append("$3").append("\r\n");
        data.append("set").append("\r\n");

        data.append("$").append(key.getBytes().length).append("\r\n");
        data.append(key).append("\r\n");

        data.append("$").append(value.getBytes().length).append("\r\n");
        data.append(value).append("\r\n");

        outputStream.write(data.toString().getBytes());
        System.out.println(data);

        byte[] res = new byte[1024];
        inputStream.read(res);
        System.out.println("接收到\n" + new String(res));
    }

    public void get(String key) throws IOException {
        StringBuffer data = new StringBuffer();
        data.append("*2").append("\r\n");

        data.append("$3").append("\r\n");
        data.append("get").append("\r\n");

        data.append("$").append(key.getBytes().length).append("\r\n");
        data.append(key).append("\r\n");

        outputStream.write(data.toString().getBytes());
        System.out.println(data);

        byte[] res = new byte[1024];
        inputStream.read(res);
        System.out.println("接收到:\n" + new String(res));
    }

    public static void main(String[] args) throws IOException {
        MyRedisClient myRedisClient = new MyRedisClient("127.0.0.1", 6379);
        myRedisClient.set("key", "value");
        System.out.println();
        myRedisClient.get("key");
    }
}
