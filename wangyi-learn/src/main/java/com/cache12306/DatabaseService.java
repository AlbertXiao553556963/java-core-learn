package com.cache12306;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author: XiaoMingxuan
 * @email: mingxuan.xmx@alibaba-inc.com
 * @create: 2018-11-18 12:55
 **/
@Component
public class DatabaseService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public Integer queryFromDatabase(String ticketSeq) {
        String sql = "select ticket_stock from tb_ticket where ticket_seq = '" + ticketSeq + "'" ;
        Map<String, Object> result =  jdbcTemplate.queryForMap(sql);
        return Integer.valueOf(result.get("ticket_stock").toString());
    }
}
