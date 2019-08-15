package concurrency;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {
    private final ReentrantLock reentrantLock = new ReentrantLock();

    public void m() {
        reentrantLock.lock();
        try {
            // ...
        } finally {
            reentrantLock.unlock();
        }
    }

    public static void main(String[] args) {

        Lock lock = new ReentrantLock();

        try {
            if (lock.tryLock(3, TimeUnit.SECONDS)) {
                // Lock API 语义补充了 synchronized 原语的不足
            }
        } catch (InterruptedException e) {
            // 重置 interrupt 状态
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
        lockOpsMethods();
    }

    private static void synchronizedStatement() {
        synchronized (ReentrantLockDemo.class) {

        }
    }

    private static void conditionObject() {
        Lock lock = new ReentrantLock();
        // 条件变量包括条件， 同时他又是线程通讯媒介
        Condition condition = lock.newCondition();
        try {
            condition.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void lock(ReentrantLock lock, int times) {
        if (times < 1) {
            return;
        }

        lock.lock();

        try {
            lock(lock, --times);
            System.out.printf("第%s次在 lock() 方法调用后的线程[%s]重进入数: %d\n",
                    times,
                    Thread.currentThread().getName(),
                    lock.getHoldCount());
        } finally {
            lock.unlock();
        }
    }

    private static void lockOpsMethods() {
        ReentrantLock lock = new ReentrantLock();
        int count = lock.getHoldCount();
        System.out.printf("在 lock() 方法调用之前的线程[%s]重进入数: %d\n", Thread.currentThread().getName(), count);
        lock(lock, 10);
        System.out.printf("在 lock() 方法调用之后的线程[%s]重进入数: %d\n", Thread.currentThread().getName(), count);
    }

}
