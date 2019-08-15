package concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.StampedLock;

public class StampLockDemo {

    public static void main(String[] args) {
        StampedLock lock = new StampedLock();
        long stamp = lock.tryOptimisticRead();
        Lock readLock = lock.asReadLock();
        try {
            readLock.lock();
            lock.validate(stamp);
        } finally {
            readLock.unlock();
        }

    }
}
