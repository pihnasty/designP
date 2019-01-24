package collection;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class MapP {
    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>();

//        Map.Entry entry = map.entrySet().iterator().next();

        Comparator comparator = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return 0;
            }
        };

        String string="Олег2 Пигнастый";
        String s2="Ивашка Иванов";
        int i = string.lastIndexOf(" ");
        int i2 = s2.lastIndexOf(" ");


        System.out.println(i+" "+i2+" "+ string.substring(i));

    }
}
