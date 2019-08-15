package rxjava;

import io.reactivex.Flowable;

public class RxJava {
    public static void main(String[] args) {
        Flowable.just("hello world").subscribe(System.out::println);
        Flowable<Integer> flow = Flowable.range(1, 5)
                .map(v -> v * v)
                .filter(v -> v % 3 == 0);
        flow.subscribe(System.out::println);
    }
}
