package com.xmx.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: XiaoMingxuan
 * @email: mingxuan.xmx@alibaba-inc.com
 * @create: 2018-12-22 22:51
 * 使用synchronized进行互斥的线程的状态blocked，使用Lock接口进行互斥的线程的状态wait
 **/
public class ThreadState {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("*****线程 new -> runnable -> terminated");
        Thread thread1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " state " + Thread.currentThread().getState());
        }, "thread(new->runnable->terminated)");
        System.out.println(thread1.getName() + " state " + thread1.getState());
        thread1.start();
        Thread.sleep(100);
        System.out.println(thread1.getName() + " state " + thread1.getState());


        System.out.println();
        System.out.println("*****线程 new -> runnable -> timedWaiting -> terminated");
        Thread thread2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " state " + Thread.currentThread().getState());
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "thread(new->runnable->timeWaiting->terminated)");
        System.out.println(thread2.getName() + " state " + thread2.getState());
        thread2.start();
        Thread.sleep(100);
        System.out.println(thread2.getName() + " state " + thread2.getState());
        Thread.sleep(500);
        System.out.println(thread2.getName() + " state " + thread2.getState());


        System.out.println();
        System.out.println("*****线程 new -> runnable -> blocked -> terminated");
        Thread thread3 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " state " + Thread.currentThread().getState());
            synchronized (ThreadState.class) {
            }
        }, "thread(new->runnable->blocked->terminated)");
        synchronized (ThreadState.class) {
            System.out.println(thread3.getName() + " state " + thread3.getState());
            thread3.start();
            Thread.sleep(100);
            System.out.println(thread3.getName() + " state " + thread3.getState());
        }
        Thread.sleep(200);
        System.out.println(thread3.getName() + " state " + thread3.getState());


        System.out.println();
        System.out.println("*****线程 new -> runnable -> wait -> terminated");
        Lock lock = new ReentrantLock();
        Thread thread4 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " state " + Thread.currentThread().getState());
            lock.lock();
            lock.unlock();
        }, "thread(new->runnable->wait->terminated)");
        lock.lock();
        try {
            System.out.println(thread3.getName() + " state " + thread4.getState());
            thread4.start();
            Thread.sleep(100);
            System.out.println(thread4.getName() + " state " + thread4.getState());
        } finally {
            lock.unlock();
        }

        Thread.sleep(200);
        System.out.println(thread4.getName() + " state " + thread4.getState());
    }
}
