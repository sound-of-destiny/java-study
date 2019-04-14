package jdk8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamTest {
    public static void main(String[] args) {
        Stream stream = Stream.of("hello", "world", "hello world");

        String[] myArray = new String[] {"hello", "world", "hello world"};
        Stream stream1 = Stream.of(myArray);
        Stream stream2 = Arrays.stream(myArray);
        List<String> list = Arrays.asList("hello", "world", "hello world");
        Stream stream3 = list.stream();

        IntStream.of(5, 6, 7).forEach(System.out::println);
        IntStream.range(3, 8).forEach(System.out::println);
        IntStream.rangeClosed(3, 8).forEach(System.out::println);

        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        System.out.println(list1.stream().map(i -> 2 * i).reduce(0, Integer::sum));



    }
}
