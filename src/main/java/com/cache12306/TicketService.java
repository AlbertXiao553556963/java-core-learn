package com.cache12306;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.Duration;


/**
 * @author: XiaoMingxuan
 * @email: mingxuan.xmx@alibaba-inc.com
 * @create: 2018-11-18 12:32
 **/
@Component
@Slf4j
public class TicketService {

    @Resource
    private DatabaseService databaseService;

    @Resource(name = "mainRedis")
    RedisTemplate mainRedis;

    public Object queryTicketStockRedis(final String ticketSeq) {

        Object stock = mainRedis.opsForValue().get(ticketSeq);

        if (stock == null) {

            /**
             * 加分布式锁，只允许一个查数据库
             */
            boolean lock = mainRedis.opsForValue().setIfAbsent(ticketSeq + "lock", "true", Duration.ofMillis(1000));

            /**
             * 没有获取到锁
             */
            while (!lock) {
                stock = mainRedis.opsForValue().get(ticketSeq);
                if (stock != null) {
                    log.info("get {} stock:{} from redis", ticketSeq, stock);
                    return stock;
                }
            }

            /**
             * 双重校验，去查询缓存是否有值
             */
            if((stock = mainRedis.opsForValue().get(ticketSeq)) != null) {
                log.info("get {} stock:{} from redis", ticketSeq, stock);
                return stock;
            }
            //查询数据库
            stock = databaseService.queryFromDatabase(ticketSeq);
            log.info("lock:{}", lock);
            log.info("get {} stock:{} from db", ticketSeq, stock);
            mainRedis.opsForValue().set(ticketSeq, stock.toString());
            // 获取到锁后一定要删除锁
            mainRedis.delete(ticketSeq + "lock");
            return stock;

        } else {
            log.info("get {} stock:{} from redis", ticketSeq, stock);
            return stock;
        }
    }

}
