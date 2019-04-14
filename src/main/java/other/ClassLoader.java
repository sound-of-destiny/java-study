package other;

import weakReference.TestClass;

public class ClassLoader {
    public static void main(String[] args) {
        TestClass test = new TestClass();
        Class a = test.getClass();
        java.lang.ClassLoader load = a.getClassLoader();
        System.out.println(load);
        System.out.println(load.getParent());
        System.out.println(load.getParent().getParent());
    }
}
