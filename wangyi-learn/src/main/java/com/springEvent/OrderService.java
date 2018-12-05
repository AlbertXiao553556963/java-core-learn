package com.springEvent;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * @author: XiaoMingxuan
 * @email: mingxuan.xmx@alibaba-inc.com
 * @create: 2018-11-25 22:40
 **/
@Service
@Slf4j
public class OrderService {

    @Autowired
    ApplicationContext applicationContext;

    public void saveOrder() {
        String orderCode = "PO123";
        log.info("1.订单创建成功:{}",orderCode);
        applicationContext.publishEvent(new OrderCreateEvent(orderCode));
        log.info("3.短信发送成功:{}",orderCode);
    }
}
