package com;

import com.miaosha.SellTicketWork;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author: XiaoMingxuan
 * @email: mingxuan.xmx@alibaba-inc.com
 * @create: 2018-12-19 09:43
 **/
@SpringBootTest(classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class TestSellTicketWork {

    @Test
    public void test() throws InterruptedException {
        SellTicketWork sellTicket = new SellTicketWork();
        for(int i = 0 ; i < 3; i++) {
            new Thread(sellTicket,"窗口" + i).start();
        }
        Thread.sleep(10000);
    }
}
