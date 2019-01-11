package com.xmx.guava;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: XiaoMingxuan
 * @email: mingxuan.xmx@alibaba-inc.com
 * @create: 2019-01-07 14:27
 **/
public class TestRateLimiter {

    private static class UserRequest implements Runnable {

        private int id;

        private static AtomicInteger atomicInteger = new AtomicInteger();

        public UserRequest() {
            this.id = atomicInteger.incrementAndGet();
        }

        public int getId() {
            return id;
        }

        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    private static class Server {
        private RateLimiter rateLimiter = RateLimiter.create(1);

        private ExecutorService threadPool = Executors.newFixedThreadPool(5);

        public void call(UserRequest userRequest) throws ExecutionException, InterruptedException {
            if (rateLimiter.tryAcquire(2, TimeUnit.SECONDS)) {
                Future result = threadPool.submit(userRequest);
                System.out.println("���� " + userRequest.id + " ��ʼִ��");
                result.get();
                System.out.println("���� " + userRequest.id + " ִ�н���");
            } else {
                System.out.println("���� " + userRequest.getId() + " �Ժ����ԣ�");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Server server = new Server();
        //ģ�Ⲣ����
        int size = 100;
        CountDownLatch countDownLatch = new CountDownLatch(1);
        CountDownLatch stop = new CountDownLatch(size);

        for (int i = 1; i <= size; i++) {
            new Thread(() -> {
                UserRequest userRequest = new UserRequest();
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    server.call(userRequest);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                stop.countDown();
            }).start();
        }
        countDownLatch.countDown();
        stop.await();
    }
}
