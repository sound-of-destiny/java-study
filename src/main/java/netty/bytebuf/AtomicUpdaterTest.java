package netty.bytebuf;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class AtomicUpdaterTest {
    public static void main(String[] args) throws InterruptedException {
        Person person = new Person();
        /*ExecutorService executorService = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 100; i++) {
            executorService.submit(() -> System.out.println(person.age++));
        }
        executorService.shutdown();
        executorService.awaitTermination(1000, TimeUnit.MINUTES);*/

        AtomicIntegerFieldUpdater<Person> atomicIntegerFieldUpdater = AtomicIntegerFieldUpdater
                .newUpdater(Person.class, "age");

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(20);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(atomicIntegerFieldUpdater.getAndIncrement(person));
            }).start();
        }
    }
}

class Person {
    volatile int age = 1;
}
