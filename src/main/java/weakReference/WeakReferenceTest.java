package weakReference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * TestClass testClass = new TestClass();
 * WeakReference<TestClass> testWeakReference = new WeakReference<>(testClass);
 * testClass = null;
 * 要想有效果必须将原来的引用置空　以上为一套模板　必须这样执行
 */

public class WeakReferenceTest {
    @Override
    protected void finalize() throws Throwable{
        System.out.println("hello world?");
    }
    public static void main(String[] args) {
        TestClass testClass = new TestClass();
        ReferenceQueue<TestClass> referenceQueue = new ReferenceQueue<>();
        WeakReference<TestClass> testWeakReference = new WeakReference<>(testClass, referenceQueue);
        System.out.println(testClass);
        System.out.println(testWeakReference.get());
        System.out.println(referenceQueue.poll());
        System.out.println("===================================");
        testClass = null;
        System.gc();
        System.out.println(testClass);
        System.out.println(testWeakReference.get());
        System.out.println(referenceQueue.poll());
    }
}
