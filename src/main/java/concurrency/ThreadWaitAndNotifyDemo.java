package concurrency;

public class ThreadWaitAndNotifyDemo {

    public static void main(String[] args) {
        Thread t1 = new Thread(ThreadWaitAndNotifyDemo::sayHello);
        t1.setName("T1");
        t1.start();

        Thread t2 = new Thread(ThreadWaitAndNotifyDemo::sayHello);
        t2.setName("T2");
        t2.start();

        synchronized (ThreadWaitAndNotifyDemo.class) {
            ThreadWaitAndNotifyDemo.class.notifyAll();
        }

    }

    public static void sayHello() {
        Thread currentThread  = Thread.currentThread();

        synchronized (ThreadWaitAndNotifyDemo.class) {
            try {
                System.out.printf("线程[%s]进入等待状态\n", currentThread.getName());
                ThreadWaitAndNotifyDemo.class.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("线程[%s]恢复执行\n", currentThread.getName());
        }
    }

}
