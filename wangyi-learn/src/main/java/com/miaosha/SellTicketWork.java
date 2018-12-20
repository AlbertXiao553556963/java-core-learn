package com.miaosha;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;

/**
 * @author: XiaoMingxuan
 * @email: mingxuan.xmx@alibaba-inc.com
 * @create: 2018-12-18 21:37
 * 使用redisson客户端作分布式锁
 **/
@Slf4j
@SuppressWarnings("all")
public class SellTicketWork implements Runnable {

    private static RedissonClient redisson;

    private static int tickets = 100;

    @Autowired
    public void setRedissonClient(RedissonClient redissonClient) {
        redisson = redissonClient;
    }

    @Override
    public void run() {
        while (true) {
            RLock rLock = redisson.getLock("SELL_TICKET");
            try {
                rLock.lock(3, TimeUnit.SECONDS);
                log.info(Thread.currentThread().getName() + "获取到锁");
                if(tickets <= 0) return;
                log.info(Thread.currentThread().getName() + "正在出售第" + tickets-- + "票");
            } catch (Exception e) {
                log.error(e.toString());
            } finally {
                rLock.unlock();
                log.info(Thread.currentThread().getName() + "释放锁");
            }
        }
    }
}
