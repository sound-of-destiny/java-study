package concurrency;

public class ThreadInterruptDemo {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(ThreadInterruptDemo::sayHelloWorld);

        thread.start();
        thread.interrupt(); //main 线程 interrupt 子线程
        thread.join(); // 等待线程结束
    }

    public static void sayHelloWorld() {

        if (Thread.currentThread().isInterrupted()) {
            System.out.printf("线程 [Id : %s] : 被interrupt!\n", Thread.currentThread().getId());
        }

        synchronized (ThreadInterruptDemo.class) {
            try {
                ThreadInterruptDemo.class.wait(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.printf("当前interrupt状态 = %s\n", Thread.currentThread().isInterrupted());
            }
        }

        System.out.println("开始睡眠！");
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (Thread.currentThread().isInterrupted()) {
            System.out.printf("线程 [Id : %s] : 被interrupt!\n", Thread.currentThread().getId());
        }

        System.out.printf("线程 [Id : %s] : Hello World!\n", Thread.currentThread().getId());
    }

}


