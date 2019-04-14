package jdk8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class Test3 {
    public static void main(String[] args) {
        MyInterface1 myInterface1 = () -> {};
        System.out.println(myInterface1.getClass().getInterfaces()[0]);
        MyInterface2 myInterface2 = () -> {};
        System.out.println(myInterface2.getClass().getInterfaces()[0]);

        new Thread(() -> System.out.println("hello world")).start();

        List<String> list = Arrays.asList("hello", "world", "ha ha");
        list.forEach(i -> System.out.println(i.toUpperCase()));
        List<String> list2 = new ArrayList<>();
        list.forEach(i -> list2.add(i.toUpperCase()));
        list2.forEach(System.out::println);
        list.stream().map(String::toUpperCase).forEach(System.out::println);
        Function<String, String> function = String::toUpperCase;
        System.out.println(function.getClass().getInterfaces()[0]);
    }
}

@FunctionalInterface
interface MyInterface1 {
    void myMethod1();
}

@FunctionalInterface
interface MyInterface2 {
    void myMethod2();
}
