package com.xmx.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * @author: XiaoMingxuan
 * @email: mingxuan.xmx@alibaba-inc.com
 * @create: 2018-12-16 11:48
 * 使用CountDownLatch模拟并发
 **/
public class SimulationConcurrency {

    static class Service {
        public static void method(int i) {
            try {
                Thread.sleep(100);
                System.out.println("方法执行成功:" + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        int threadSize = 1000;
        CountDownLatch stop = new CountDownLatch(threadSize);
        CountDownLatch start = new CountDownLatch(threadSize);

        for(int i = 0; i < threadSize; i++) {
            int value = i;
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + "准备中");
                    Random random = new Random();
                    Thread.sleep(random.nextInt(100) + 800);
                    System.out.println(Thread.currentThread().getName() + "准备完毕`");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                try {
                    start.countDown();
                    start.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Service.method(value);
                stop.countDown();
            }).start();
        }

        stop.await();

    }

}
