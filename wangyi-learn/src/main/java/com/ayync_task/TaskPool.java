package com.ayync_task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author: XiaoMingxuan
 * @email: mingxuan.xmx@alibaba-inc.com
 * @create: 2018-12-10 14:31
 **/
@Component
@Slf4j
public class TaskPool {

    @Async("MyTaskExecutor")
    public void task1() {
        log.info(Thread.currentThread().getName() + "运行中");
        log.info(Thread.currentThread().getName() + "运行结束");
    }
}
