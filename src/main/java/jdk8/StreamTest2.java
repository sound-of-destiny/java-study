package jdk8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class StreamTest2 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(5000000);
        for (int i = 0; i < 5000000; i++) {
            list.add(UUID.randomUUID().toString());
        }

        System.out.println("开始排序");

        long startTime = System.nanoTime();
        list.parallelStream().sorted().forEach((str) -> {});
        long endTime = System.nanoTime();
        long millis = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);
        System.out.println("排序时间" + millis);

        List<String> list1 = Arrays.asList("Hi", "Hello", "你好");
        List<String> list2 = Arrays.asList("zhang san", "li shi", "wang wu", "hhh");

        List<String> result = list1.stream().flatMap(item -> list2.stream().map((item2 -> item + item2))).collect(Collectors.toList());
        result.forEach(System.out::println);
    }

}
