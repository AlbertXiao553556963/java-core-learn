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
        Scanner scanner = new Scanner(System.in);
        System.out.println("input:");
        String msg = scanner.nextLine();
        out.write(msg.getBytes());
        scanner.close();
        out.flush();
        out.close();
        socket.close();
    }
}
