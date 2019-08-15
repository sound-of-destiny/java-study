package concurrency;

import java.security.SecureRandom;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ProducerComsumerProblemDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Container container = new Container();
        Future pfuture = executorService.submit(container::produce);
        Future cfuture = executorService.submit(container::consume);

        //pfuture.get();
        //cfuture.get();

        Thread.sleep(1000L);
        executorService.shutdown();
    }

    public static class Container {
        private List<Integer> data = new LinkedList<>();

        private SecureRandom secureRandom = new SecureRandom();

        private static final int MAX_SIZE = 5;
        public void produce() {
            for(;;) {
                synchronized (this) {
                    try {
                        while (data.size() >= MAX_SIZE) {
                            wait();
                        }
                        int value = secureRandom.nextInt(100);
                        System.out.printf("线程[%s]正在生产数据: %d\n", Thread.currentThread().getName(), value);
                        data.add(value);

                        notify();
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        public void consume() {
            for(;;) {
                synchronized (this) {
                    try {
                        while (data.isEmpty()) {
                            wait();
                        }
                        int value = data.remove(0);
                        System.out.printf("线程[%s]正在消耗数据: %d\n", Thread.currentThread().getName(), value);

                        notify();
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
