package jdk8;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class Test1 {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8,
                1, 2, 3, 4, 5, 6, 7, 8,
                1, 2, 3, 4, 5, 6, 7, 8,
                1, 2, 3, 4, 5, 6, 7, 8,
                1, 2, 3, 4, 5, 6, 7, 8,
                1, 2, 3, 4, 5, 6, 7, 8,
                1, 2, 3, 4, 5, 6, 7, 8,
                1, 2, 3, 4, 5, 6, 7, 8,
                1, 2, 3, 4, 5, 6, 7, 8,
                1, 2, 3, 4, 5, 6, 7, 8,
                1, 2, 3, 4, 5, 6, 7, 8,
                1, 2, 3, 4, 5, 6, 7, 8,
                1, 2, 3, 4, 5, 6, 7, 8,

                1, 2, 3, 4, 5, 6, 7, 8,1, 2, 3, 4, 5, 6, 7, 8,

                1, 2, 3, 4, 5, 6, 7, 8,1, 2, 3, 4, 5, 6, 7, 8,

                1, 2, 3, 4, 5, 6, 7, 8,1, 2, 3, 4, 5, 6, 7, 8,

                1, 2, 3, 4, 5, 6, 7, 8,1, 2, 3, 4, 5, 6, 7, 8,

                1, 2, 3, 4, 5, 6, 7, 8,1, 2, 3, 4, 5, 6, 7, 8,

                1, 2, 3, 4, 5, 6, 7, 8,1, 2, 3, 4, 5, 6, 7, 8,
                1, 2, 3, 4, 5, 6, 7, 8,1, 2, 3, 4, 5, 6, 7, 8


        );

        // <= 1.4
        System.out.println(LocalDateTime.now());
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i));
        }

        System.out.println("----------");
        System.out.println(LocalDateTime.now());
        // 1.5
        for(int i : list) {
            System.out.print(i);
        }

        System.out.println("----------");
        System.out.println(LocalDateTime.now());
        // 1.8
        list.forEach(System.out::print);
        System.out.println("----------");
        System.out.println(LocalDateTime.now());

    }
}
