package jdk8;

import java.util.function.Function;

public class FunctionTest {
    public static void main(String[] args) {
        FunctionTest test = new FunctionTest();
        System.out.println(test.compute(1, value -> 2*value));
        Function<Integer, Integer> function = value -> 2*value;
        System.out.println(test.compute(2, function));
    }

    public int compute(int a, Function<Integer, Integer> function) {
        int result = function.apply(a);
        return result;
    }
}
