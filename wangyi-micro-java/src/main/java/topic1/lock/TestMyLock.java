package topic1.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;

/**
 * @author: XiaoMingxuan
 * @email: mingxuan.xmx@alibaba-inc.com
 * @create: 2019-01-11 19:25
 **/
public class TestMyLock {
    int sum;

//    Lock lock = new ReentrantLock(); //�������
//    Lock lock = new MyReentrantLock(); //�����м�
    Lock lock = new MyReentrantLockV2(); //�������

    public void add() {
//        sum++;
        lock.lock();
        try {
            sum++;
        } finally {
            lock.unlock();
        }
    }
    public static void main(String[] args) throws InterruptedException {

        TestMyLock test = new TestMyLock();
        int threadSize = 100;
        CountDownLatch countDownLatch = new CountDownLatch(threadSize);

        Long start = System.currentTimeMillis();

        for(int i = 0; i < threadSize; i++) {
            new Thread(() -> {
                for(int j = 0; j < 4000; j++) {
                    test.add();
                }
                countDownLatch.countDown();
            }).start();
        }

        countDownLatch.await();
        System.out.println(test.sum);

        System.out.println("��ʱ:" + (System.currentTimeMillis() - start));
    }
}
