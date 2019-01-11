package topic1.thread;

import java.text.SimpleDateFormat;
import java.util.concurrent.*;

/**
 * @author: XiaoMingxuan
 * @email: mingxuan.xmx@alibaba-inc.com
 * @create: 2018-12-28 22:37
 **/
public class ThreadPool2 {

    public void testCommon(ThreadPoolExecutor threadPoolExecutor) throws InterruptedException {
        System.out.println("before任务调度-当前线程池中线程数量" + threadPoolExecutor.getPoolSize());
        System.out.println("before任务调度-当前线程池中任务队列数量" + threadPoolExecutor.getQueue().size());
        for (int i = 0; i < 15; i++) {
            threadPoolExecutor.submit(() -> {
                System.out.println(Thread.currentThread().getName() + " was started");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.err.println(Thread.currentThread().getName() + " was finished");
            });
        }
        Thread.sleep(10);
        System.out.println("after任务调度-当前线程池中线程数量" + threadPoolExecutor.getPoolSize());
        System.out.println("after任务调度-当前线程池中任务队列数量" + threadPoolExecutor.getQueue().size());
        Thread.sleep(150 * 15);
        System.out.println("finally任务调度-当前线程池中线程数量" + threadPoolExecutor.getPoolSize());
        System.out.println("finally任务调度-当前线程池中任务队列数量" + threadPoolExecutor.getQueue().size());
        Thread.sleep(150);
        System.out.println("stop任务调度-当前线程池中线程数量" + threadPoolExecutor.getPoolSize());
        System.out.println("stop任务调度-当前线程池中任务队列数量" + threadPoolExecutor.getQueue().size());
        threadPoolExecutor.shutdown();
    }

    /**
     * 线程池信息： 核心线程数量5，最大数量10，无界队列，超出核心线程数量的线程存活时间：5秒， 指定拒绝策略的
     *
     * @throws InterruptedException
     */
    public void threadPoolExecutorTest1() throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 5, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>());
        testCommon(threadPoolExecutor);
    }

    /**
     * 线程池信息： 核心线程数量5，最大数量10，队列数目2，超出核心线程数量的线程存活时间：5秒， 指定拒绝策略的
     *
     * @throws InterruptedException
     */
    public void threadPoolExecutorTest2() throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                5, 10,
                5, TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<>(2),
                (r, executor) -> System.err.println("有任务被拒绝执行了")
        );
        testCommon(threadPoolExecutor);
    }

    /**
     * 线程池信息： 核心线程数量0，最大数量Integer.MAX_VALUE，SynchronousQueue队列，超出核心线程数量的线程存活时间：60秒
     *
     * @throws InterruptedException
     */
    public void threadPoolExecutorTest3() throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                0, Integer.MAX_VALUE,
                60000, TimeUnit.MILLISECONDS,
                new SynchronousQueue<>(),
                (r, executor) -> System.err.println("有任务被拒绝执行了")
        );
        testCommon(threadPoolExecutor);
    }

    /**
     * 定时执行线程池信息：3秒后执行，一次性任务
     * 核心线程数量5，最大数量Integer.MAX_VALUE，DelayedWorkQueue延时队列，超出核心线程数量的线程存活时间：0秒
     *
     * @throws Exception
     */
    private void threadPoolExecutorTest4() throws Exception {
        SimpleDateFormat dataF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(1);
        scheduledThreadPoolExecutor.schedule(
                () -> System.out.println("任务被执行:" + dataF.format(System.currentTimeMillis())),
                3000, TimeUnit.MILLISECONDS
        );
        System.out.println("定时任务提交成功:" + dataF.format(System.currentTimeMillis()));
        scheduledThreadPoolExecutor.shutdown();

    }

    public static void main(String[] args) throws Exception {
//        new ThreadPool2().threadPoolExecutorTest1();
//        new ThreadPool2().threadPoolExecutorTest2();
//        new ThreadPool2().threadPoolExecutorTest3();
        new ThreadPool2().threadPoolExecutorTest4();
    }
}
