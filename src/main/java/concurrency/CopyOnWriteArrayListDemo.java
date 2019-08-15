package concurrency;

import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListDemo {

    public static void main(String[] args) {
        CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>();

        // 安装 Thread ID
        list.add(1);
        // 判断当前线程 ID == main.threadId
        list.add(2);
        list.add(3);
        // Copy
        // JDK 升级两大核心性能提升
        // 1. 数组
        // 2. String

        list.forEach(System.out::println);

    }
}
