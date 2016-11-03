package tests;

import java.util.WeakHashMap;

/**
 * Created by Pihnastyi.O on 11/3/2016.
 */
public class testWeakHashMap {
    public static void main (String [] args) {
        WeakHashMap<Object, Object> weakHashMap = new WeakHashMap<>();
        weakHashMap.put("1","value1");
        weakHashMap.put(null,"value2");
        weakHashMap.put("2",null);
        System.out.println(weakHashMap.size());


        System.out.println(weakHashMap.get(null));
        System.out.println(weakHashMap.get("2"));

        weakHashMap.put(null,null);
        System.out.println(weakHashMap.size());

        System.out.println(weakHashMap.get(null));

    }
}
