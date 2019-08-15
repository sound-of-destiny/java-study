package weakReference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

public class PhantomReferenceDemo {
    public static void main(String[] args) {
        TestClass testClass = new TestClass();
        ReferenceQueue<TestClass> referenceQueue = new ReferenceQueue<>();
        PhantomReference<TestClass> phantomReference = new PhantomReference<>(testClass, referenceQueue);

        System.out.println(testClass);
        System.out.println(phantomReference.get());
        System.out.println(referenceQueue.poll());

        System.out.println("==================================");

        testClass = null;
        System.gc();

        System.out.println(testClass);
        System.out.println(phantomReference.get());
        System.out.println(referenceQueue.poll());
    }
}
