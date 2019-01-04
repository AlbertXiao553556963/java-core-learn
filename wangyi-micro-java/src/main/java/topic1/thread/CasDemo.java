package topic1.thread;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.CountDownLatch;

/**
 * @author: XiaoMingxuan
 * @email: mingxuan.xmx@alibaba-inc.com
 * @create: 2019-01-04 18:59
 * Unsafe类cas修改内存地址
 **/
public class CasDemo {

//    static volatile int value; // volatile只能保证读取的是内存中的最新的、写入后立刻刷回内存
    int value;

    static Unsafe unsafe;

    static long valueOffset;

    static {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);
            Field f = CasDemo.class.getDeclaredField("value");
            valueOffset = unsafe.objectFieldOffset(f);
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(2);

        for(int i = 0; i < 2; i++) {
            new Thread(() -> {
                for(int j = 0; j < 10000; j++) {
                    // CAS + 循环 重试
                    int current;
                    do {
                        // 操作耗时的话， 那么 线程就会占用大量的CPU执行时间
                        current = unsafe.getIntVolatile(CasDemo.class, valueOffset);
                    } while (!unsafe.compareAndSwapInt(CasDemo.class, valueOffset, current, current + 1));
                    // 可能会失败
                }
                System.out.println(Thread.currentThread().getName() + " was finished");
                latch.countDown();
            }).start();
        }
        latch.await();
        System.out.println(unsafe.getIntVolatile(CasDemo.class, valueOffset));
    }
}
