package tests.core.map.hashmap;

import com.amazon.sct.util.PrinterUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Created by Pihnastyi.O on 6/15/2017.
 */
public class HashMapPut {
    public static void main(String[] args) {
        Map<String,String> hashmap = new HashMap<>();

        String s = hashmap.put("A1","A1");

        System.out.println(s);
        System.out.println(hashmap.put("B1","B1"));
        System.out.println(hashmap.put(null,"A1"));
        System.out.println(hashmap.size());

        HashMapPut hashMapPut = new HashMapPut();
        hashMapPut.printProfileParameters(hashmap);


    }


    private String printProfileParameters(Map<String, String> profileParameters) {
        Function<Long, Long> maxLengthKey = new Function<Long, Long>() {
            private Long keyNameLength = 0L;

            @Override
            public Long apply(Long keyLength) {
                if (keyNameLength < keyLength) {
                    keyNameLength = keyLength;
                }
                return keyNameLength;
            }
        };
        profileParameters.keySet()
            .forEach(
                key ->
                    maxLengthKey
                        .apply(
                            (long) key.length()));

        StringBuilder statement = new StringBuilder();
        profileParameters.entrySet().forEach(profileParameter -> {
            PrinterUtils.lsInd(statement);
            String resourceNameFormat = "%-" + maxLengthKey.apply(-1L).toString()
                + "s" + "  " + "%-" + maxLengthKey.apply(-1L).toString() + "s";
            statement.append(String.format(resourceNameFormat, profileParameter.getKey(), profileParameter.getValue()));
        });
        return statement.toString();
    }

}

/*

null
A1




* */