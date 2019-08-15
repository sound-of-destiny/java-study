package ABA;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

public class ABADemo {

    private static AtomicStampedReference<Integer> atomicReference = new AtomicStampedReference<>(100, 1);
    public static void main(String[] args) {
        new Thread(() -> {
            atomicReference.compareAndSet(100, 101,1, 2);
            atomicReference.compareAndSet(101, 100,2, 3);
        }, "t1").start();
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(atomicReference.compareAndSet(100, 2019, 3, 4)
                + "\t" + atomicReference.getReference());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t2").start();
    }
}
