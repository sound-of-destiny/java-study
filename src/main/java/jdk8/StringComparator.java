package jdk8;

import java.util.*;

public class StringComparator {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("zhang san", "lis hi", "wang wu");
        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        System.out.println(names);

        Collections.sort(names, String::compareTo);
    }
}
