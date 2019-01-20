package topic1.lock;

import java.util.Iterator;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;

/**
 * 使用park机制和阻塞队列实现可重入锁
 * @author: XiaoMingxuan
 * @email: mingxuan.xmx@alibaba-inc.com
 * @create: 2019-01-11 18:44
 **/
public class MyReentrantLockV2 implements Lock {

    private AtomicReference<Thread> owner = new AtomicReference<>();

    private LinkedBlockingQueue<Thread> waiters = new LinkedBlockingQueue<>(1000);

    @Override
    public boolean tryLock() {
        return owner.compareAndSet(null, Thread.currentThread());
    }

    @Override
    public void lock() {
        boolean isAdd = true;
        while (!tryLock()) {
            if(isAdd) {
                waiters.add(Thread.currentThread());
                isAdd = false;
            }
            LockSupport.park();
        }
        waiters.remove(Thread.currentThread());
    }

    @Override
    public void unlock() {
        if(owner.compareAndSet(Thread.currentThread(), null)) {
            Iterator<Thread> iterator = waiters.iterator();
            while (iterator.hasNext()) {
                Thread waiter = iterator.next();
                LockSupport.unpark(waiter);
            }
        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }



    @Override
    public Condition newCondition() {
        return null;
    }

}
