package com.springEvent;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author: XiaoMingxuan
 * @email: mingxuan.xmx@alibaba-inc.com
 * @create: 2018-11-25 23:10
 **/
@Component
@Slf4j
public class OrderCreateSmsListener implements ApplicationListener<OrderCreateEvent> {

    @Override
    @Async
    public void onApplicationEvent(OrderCreateEvent orderCreateEvent) {
        log.info("2.短信通知，订单号:{}", orderCreateEvent.getSource());
    }

}
