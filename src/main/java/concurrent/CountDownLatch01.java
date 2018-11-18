package concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * @author: XiaoMingxuan
 * @email: mingxuan.xmx@alibaba-inc.com
 * @create: 2018-11-13 21:39
 **/
public class CountDownLatch01 {

    static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) throws InterruptedException {
        countDownLatch.countDown();
        countDownLatch.countDown();
        countDownLatch.await();
        System.out.println("a");
    }

}
