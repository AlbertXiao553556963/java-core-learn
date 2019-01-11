package topic1.lock;

/**
 * @author: XiaoMingxuan
 * @email: mingxuan.xmx@alibaba-inc.com
 * @create: 2019-01-05 21:57
 **/
public class MyReentrantLock {

    private volatile boolean isLocked = false;

    private Thread lockBy;

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

    public synchronized void unLock() {
        isLocked = false;
        lockBy = null;
        notify();
    }

    public static void main(String[] args) {
        MyReentrantLock lock = new MyReentrantLock();
        lock.lock();
        System.out.println("first");
        lock.lock();
        System.out.println("second");
    }
}
