package concurrency;

import java.util.concurrent.Executors;

public class StopAThread {
    public static void main(String[] args) throws InterruptedException {
        StopableAction action = new StopableAction();
        Thread thread = new Thread(action);

        // 线程启动(并不一定执行)
        thread.start();

        // main 线程调整stopped状态到true
        action.stop();

        thread.join();

        // 如果线程池的话, 以上方案可能存在问题
        Executors.newFixedThreadPool(2);
        Executors.newCachedThreadPool(); // 几乎无限的线程

    }

    private static class StopableAction implements Runnable {

        /**
         * 确保 stopped 原子操作, 等价于 AtomicBoolean
         */
        private volatile boolean stopped;

        @Override
        public void run() {

            if (stopped) {
                // 通过异常的方式, 可以 kill 线程
                System.out.println("Action 终止");
                return;
            }

            System.out.println("Action 执行");
        }

        public void stop() {
            this.stopped = true;
        }
    }
}
