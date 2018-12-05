package com.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author: XiaoMingxuan
 * @email: mingxuan.xmx@alibaba-inc.com
 * @create: 2018-11-22 22:37
 **/
public class TestVolatile {

    static volatile boolean flag = true;

    public static void main(String[] args) {

        Thread t = new Thread( () -> {
            int i = 0;
            while (flag) {
                i++;
            }
            System.out.println(i);
        });
        t.start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        flag = false;
    }
}
