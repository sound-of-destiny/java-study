package concurrency;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

public class ProcessIdDemo {

    public static void main(String[] args) {
        // java 9 之前的实现
        getProcessIdInLegacyWay();

        // java 9 之后的方法
        getProcessIdInJava9();
        getProcessIdInJava10();

    }

    private static void getProcessIdInJava10() {
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        System.out.println("[java 10+ 的方法] 当前进程 ID : " + runtimeMXBean.getPid());
    }

    private static void getProcessIdInJava9() {
        long pid = ProcessHandle.current().pid();
        System.out.println("[java 9+ 的方法] 当前进程 ID : " + pid);
    }

    private static void getProcessIdInLegacyWay() {
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        String name = runtimeMXBean.getName();
        System.out.println("[java  9 之前的方法] 当前进程 ID : " + name.substring(0, name.indexOf("@")));
    }

}
