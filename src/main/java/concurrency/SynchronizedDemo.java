package concurrency;

public class SynchronizedDemo {

    public static void main(String[] args) {
        synchronized (SynchronizedDemo.class) {
            Data data = new Data();
            data.setValue(1);
        }
    }

    private static class Data {
        /**
         * 线程不安全
         */
        private volatile int value;

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }
}
