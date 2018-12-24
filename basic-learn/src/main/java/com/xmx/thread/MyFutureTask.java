package com.xmx.thread;

import java.util.concurrent.Callable;

/**
 * @author: XiaoMingxuan
 * @email: mingxuan.xmx@alibaba-inc.com
 * @create: 2018-12-08 12:41
 * 模拟FurtureTask实现
 **/
public class MyFutureTask<T> implements Runnable {

    Callable<T> callable;

    T result;

    volatile boolean finish = false;

    public MyFutureTask(Callable<T> callable) {
        this.callable = callable;
    }

    public T get() {
        if(finish == true) {
            return result;
        }

        synchronized (this) {
            while (!finish) {
                try {
                    System.out.println("未完成阻塞");
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }

    @Override
    public void run() {
        try {
            result = callable.call();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            finish = true;
            synchronized (this) {
                System.out.println(Thread.currentThread().getName() + ":任务完成，通知");
                this.notify();
            }
        }
    }
}
