package com.xmx.network.bio;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author: XiaoMingxuan
 * @email: mingxuan.xmx@alibaba-inc.com
 * @create: 2019-01-23 17:43
 **/
public class BioClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 8080);

        OutputStream out = socket.getOutputStream();

        out.write("hello this is client\n".getBytes());

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String msg;
        while ( (msg = bufferedReader.readLine()) != null) {
            System.out.println(msg);
        }

        socket.close();
    }
}
