package topic1.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author: XiaoMingxuan
 * @email: mingxuan.xmx@alibaba-inc.com
 * @create: 2019-01-05 21:57
 **/
public class MyReentrantLock implements Lock {

    private volatile boolean isLocked = false;

    private Thread lockBy;

    @Override
    public synchronized void lock() {
        while (isLocked && Thread.currentThread() != lockBy) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        lockBy = Thread.currentThread();
        isLocked = true;
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public synchronized void unlock() {
        isLocked = false;
        lockBy = null;
        notifyAll();
    }

    @Override
    public Condition newCondition() {
        return null;
    }

}
