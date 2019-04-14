package jdk8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class PredicateTest {
    public static void main(String[] args) {
        Predicate<String> predicate = p -> p.length() > 5;
        System.out.println(predicate.test("hello1"));
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        PredicateTest predicateTest = new PredicateTest();
        predicateTest.conditionFilter(list, i -> i > 5);
        predicateTest.conditionFilter2(list, i -> i > 5, i -> i % 2 == 0);


    }

    public void conditionFilter(List<Integer> list, Predicate<Integer> predicate) {
        list.forEach(i -> { if (predicate.test(i)) System.out.println(i);});
    }

    public void conditionFilter2(List<Integer> list, Predicate<Integer> predicate1, Predicate<Integer> predicate2) {
        list.forEach(i -> { if (predicate1.and(predicate2).test(i)) System.out.println(i);});
    }
}
