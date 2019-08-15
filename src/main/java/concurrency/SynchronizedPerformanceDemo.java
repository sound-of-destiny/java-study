package concurrency;

import java.util.ArrayList;
import java.util.Vector;
import java.util.function.Consumer;

public class SynchronizedPerformanceDemo {

    public static void main(String[] args) {
        Vector vector = new Vector();
        ArrayList arrayList = new ArrayList();

        testAdd(vector);
        System.gc();
        testAdd(arrayList);
        System.gc();
    }

    private static void testAdd(ArrayList arrayList) {
        doTest(10000, arrayList::add);
    }

    private static void testAdd(Vector vector) {
        doTest(10000, vector::add);
    }

    private static void doTest(int count, Consumer<Object> consumer) {
        long startTime = System.nanoTime();
        for (int i = 0; i < count; i++) {
            consumer.accept(new Object());
        }
        long endTime = System.nanoTime() - startTime;
        System.out.println(endTime);
    }

}
