package weakReference;

import java.lang.ref.WeakReference;

public class WeakReferenceTest {
    @Override
    protected void finalize() throws Throwable{
        System.out.println("hello world?");
    }
    public static void main(String[] args) {
        TestClass testClass = new TestClass();
        WeakReference<TestClass> testWeakReference = new WeakReference<>(testClass);
        System.gc();
        System.out.println("hello " + testWeakReference.get());
    }
}
