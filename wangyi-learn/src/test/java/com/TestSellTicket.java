package com;

import com.miaosha.SellTicket;
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
public class TestSellTicket {

    @Test
    public void test() {
        SellTicket.SellWindow sellWindow = new SellTicket.SellWindow();

        for(int i = 0 ; i < 3; i++) {
            new Thread(sellWindow,"窗口" + i).start();
        }
    }
}
