package com;

import com.async_task.TaskPool;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author: XiaoMingxuan
 * @email: mingxuan.xmx@alibaba-inc.com
 * @create: 2018-12-10 14:31
 **/
@SpringBootTest(classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class TestAsyncTask {

    @Autowired
    TaskPool tasks;

    @Test
    public void test01() {
        tasks.task1();
    }
}
