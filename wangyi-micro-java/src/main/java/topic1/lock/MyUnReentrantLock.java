package topic1.lock;

/**
 * @author: XiaoMingxuan
 * @email: mingxuan.xmx@alibaba-inc.com
 * @create: 2019-01-05 21:57
 **/
public class MyUnReentrantLock {

    private volatile boolean isLocked = false;

    public synchronized void lock() {
        while (isLocked) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        isLocked = true;
    }

    public synchronized void unLock() {
        isLocked = false;
        notify();
    }

    public static void main(String[] args) {
        MyUnReentrantLock lock = new MyUnReentrantLock();
        lock.lock();
        System.out.println("first");
        lock.lock();
        System.out.println("second");
    }
}
