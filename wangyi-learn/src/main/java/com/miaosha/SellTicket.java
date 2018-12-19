package com.miaosha;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * @author: XiaoMingxuan
 * @email: mingxuan.xmx@alibaba-inc.com
 * @create: 2018-12-18 21:37
 * 使用redisson客户端作分布式锁
 **/
@Component
public class SellTicket {

    @Autowired
    private static RedissonClient redisson;

    private static RLock rLock;

    @Autowired
    public void setRedissonClient(RedissonClient redisson) {
        SellTicket.redisson = redisson;

    }

    public static class SellWindow implements Runnable {

        private int tickets = 100;

        @Override
        public void run() {
            while (true) {
                rLock = redisson.getLock("SELL_TICKET");
                rLock.lock(3, TimeUnit.SECONDS);
                try {
                    if(tickets == 0) return;
                    System.out.println(Thread.currentThread().getName() + "正在出售第" + tickets-- + "票");
                } catch (Exception e) {

                } finally {
                    rLock.unlock();
                }
            }
        }
    }
}
