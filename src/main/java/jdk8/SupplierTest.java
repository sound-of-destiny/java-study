package jdk8;

import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;

public class SupplierTest {
    public static void main(String[] args) {
        Supplier<String> stringSupplier = () -> "hello world";
        System.out.println(stringSupplier.get());

        Supplier<Person> supplier = Person::new;
        BiFunction<String, Integer, Person> biFunction = Person::new;
        System.out.println(supplier.get().getUsername());
        System.out.println(biFunction.apply("hello", 2).getUsername());
        SupplierTest supplierTest = new SupplierTest();
        System.out.println(supplierTest.doBinaryOperator(1, 2, Integer::sum));
    }

    public int doBinaryOperator(int a, int b, BinaryOperator<Integer> binaryOperator) {
        return binaryOperator.apply(a, b);
    }


}
