package concurrency.example;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class CountExample {

    // 1
    private static volatile long count = 0;
    // 2
    private static Map<Integer, Integer> map = Maps.newHashMap();

    public static void main(String[] args) throws InterruptedException {

        int threadTotal = 200;
        int clientTotal = 5000;

        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);

        for (int index = 0; index < clientTotal; index++) {
            final int n = index;
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    synchronized (CountExample.class) {
                        count++;
                    }
                    //map.put(n, n);
                    semaphore.release();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        executorService.shutdown();
        if (executorService.awaitTermination(1000, TimeUnit.MILLISECONDS)) {
            System.out.println(count);
            // System.out.println(map.size());
        } else {
            System.out.println("some not completion!");
        }

    }
}
