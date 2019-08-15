package concurrency;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.RuntimeMXBean;
import java.lang.management.ThreadMXBean;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class ProcessInfoDemo {
    public static void main(String[] args) {
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();

        long pid  = ProcessHandle.current().pid();
        System.out.println("[java 9+ 的方法] 当前进程 ID : " + pid);

        Instant instant = Instant.ofEpochMilli(runtimeMXBean.getStartTime());
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        System.out.println("当前进程启动时间： " + localDateTime);
        System.out.println("当前进程上线时间： " + runtimeMXBean.getUptime());

        System.out.println("当前进程线程数量： " + threadMXBean.getThreadCount());

        ManagementFactory.getMemoryManagerMXBeans().forEach(memoryManagerMXBean ->
            System.out.println(memoryManagerMXBean.getName())
        );

        System.out.println(operatingSystemMXBean.getArch());

        System.exit(0);
    }
}
