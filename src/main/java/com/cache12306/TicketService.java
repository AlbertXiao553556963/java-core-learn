package com.cache12306;

import lombok.extern.slf4j.Slf4j;
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

    public Integer queryTicketStock(final String ticketSeq) {
        Integer stock;
        stock = databaseService.queryFromDatabase(ticketSeq);
        log.info("get {} stock:{} from db", ticketSeq, stock);
        return stock;
    }
}
