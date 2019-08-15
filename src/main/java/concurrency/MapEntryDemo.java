package concurrency;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

public class MapEntryDemo {

    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();

        demoMap(map);

        System.out.println(map);

        Map<String, Integer> skipListMap = new ConcurrentSkipListMap<>();

        demoMap(skipListMap);

        System.out.println(skipListMap);
    }

    private static void demoMap(Map<String, Integer> map) {
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);

        // 问题: 如何让所有成员值 +1
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            slowApproach(entry, map);
            fastApproach(entry);
        }
    }


    private static void fastApproach(Map.Entry<String, Integer> entry) {
        entry.setValue(entry.getValue() + 1);
    }

    private static void slowApproach(Map.Entry<String, Integer> entry, Map<String, Integer> map) {
        String key = entry.getKey();
        Integer value = entry.getValue();
        value += 1;
        map.put(key, value);
    }

}
