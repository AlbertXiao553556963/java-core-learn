package com.cache12306;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


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

    public void queryTicketStock(final String ticketSeq) {
        Object stock = mainRedis.opsForValue().get(ticketSeq);
        if(stock == null) {
            stock = databaseService.queryFromDatabase(ticketSeq);
            String value = stock.toString();
            mainRedis.opsForValue().setIfAbsent(ticketSeq, value);
            log.info("get {} stock:{} from db", ticketSeq, stock);
        } else {
            log.info("get {} stock:{} from redis", ticketSeq, stock);
        }
    }
}
