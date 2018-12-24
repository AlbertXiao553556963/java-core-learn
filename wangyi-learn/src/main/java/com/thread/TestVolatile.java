package com.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author: XiaoMingxuan
 * @email: mingxuan.xmx@alibaba-inc.com
 * @create: 2018-11-22 22:37
 * volatile变量演示
 **/
public class TestVolatile {

    /**
     * 如果变量flag不用volatile修饰，造成死循环，因为编译器优化和java线程本地缓存
     */
    static boolean flag = true;

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            int i = 0;
            while (flag) {
                i = 1;
            }
            System.out.println(i);
        });
        t.start();

        TimeUnit.SECONDS.sleep(2);

        flag = false;
        System.out.println(flag);
    }
}
