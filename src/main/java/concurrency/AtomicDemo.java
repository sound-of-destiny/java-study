package concurrency;

import sun.misc.Unsafe;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.concurrent.atomic.AtomicBoolean;

public class AtomicDemo {
    public static void main(String[] args) throws Exception {

    }

    private static void demoAtomicBoolean() {
        AtomicBoolean b = new AtomicBoolean();

    }

    private static void getUnsafe() throws PrivilegedActionException {
        // 无法通过正常方式调用
        // Unsafe unsafe = Unsafe.getUnsafe();

        final PrivilegedExceptionAction<Unsafe> action = new PrivilegedExceptionAction<Unsafe>() {
            @Override
            public Unsafe run() throws Exception {
                Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
                theUnsafe.setAccessible(true);
                return (Unsafe) theUnsafe.get(null);
            }
        };

        Unsafe unsafe = AccessController.doPrivileged(action);

        if (unsafe == null) {
            throw new NullPointerException();
        }

        // unsafe.
    }

    private static void getVarHandle() throws NoSuchFieldException, IllegalAccessException {
        MethodHandles.Lookup l = MethodHandles.lookup();
        VarHandle varHandle = l.findVarHandle(AtomicDemo.class,"value", int.class);
        // varHandle.
    }

}
