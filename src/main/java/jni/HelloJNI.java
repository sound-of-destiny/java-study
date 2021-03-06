package jni;

public class HelloJNI {
    static {
        System.loadLibrary("hello"); // Load native library at runtime
        // hello.dll (Windows) or libhello.so (Unixes)
    }

    // Declare a native method sayHello() that receives nothing and returns void
    private native void sayHello();

    // Test Driver
    public static void main(String[] args) {
        System.out.println(System.getProperty("java.library.path"));
        new HelloJNI().sayHello();  // invoke the native method
    }
}
