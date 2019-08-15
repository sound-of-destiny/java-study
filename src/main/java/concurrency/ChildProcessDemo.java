package concurrency;

import java.io.IOException;
import java.io.InputStream;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;

public class ChildProcessDemo {

    // IDEA(主进程） -> ChildProcessDemo -> wireshark
    public static void main(String[] args) throws IOException {
        OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();

        if (operatingSystemMXBean.getName().startsWith("Linux")) {
            // 启动终端
            Process process = Runtime.getRuntime().exec("/usr/lib/gnome-terminal");
            InputStream inputStream = process.getInputStream();
            int data = 0;
            while((data = inputStream.read()) > -1) {
                System.out.println(data);
            }
        }

    }
}
