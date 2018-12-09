package com;

import com.cache12306.TicketService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author: XiaoMingxuan
 * @email: mingxuan.xmx@alibaba-inc.com
 * @create: 2018-11-19 11:49
 **/
@SpringBootTest(classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class TestCache12306 {

    @Autowired
    TicketService ticketService;

    final Integer concurrenceSize = 1000;

    @Test
    public void test01() {
        ticketService.queryTicketStockRedis("G104");
    }

    @Test
    public void test02() {
        CountDownLatch countDownLatch = new CountDownLatch(concurrenceSize);

        List<Thread> threads = new ArrayList<>();
        long start = System.currentTimeMillis();
        for(int i = 0; i < concurrenceSize; i++) {
            int finalI = i;
            Thread t = new Thread(() -> {
                try {
                    log.info("thread {} is ready", String.valueOf(finalI));
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ticketService.queryTicketStockRedis("G104");
            });
            threads.add(t);
            t.start();
            countDownLatch.countDown();
        }

        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println("total time:" + (System.currentTimeMillis() - start) );
    }
}
