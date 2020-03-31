package collection.generic;

import java.util.ArrayList;
import java.util.List;

public class GenArray {

    public static void main(String[] args) {
        String[] strings = new String[] {"a", "b", "c"};
        Object[] arr = strings;
        arr[0] = 4;

//        List<Integer> ints = Arrays.asList(1,2,3);
//        List<Number> nums = ints; // compile-time error. Проблема обнаружилась на этапе компиляции
//        nums.set(2, 3.14);
//        assert ints.toString().equals("[1, 2, 3.14]");

        ArrayList<? super Number> listNumber = new ArrayList<>();

        Integer n = 12;
        listNumber.add(n);

        Integer n2 = 12;
        listNumber.add(n2);

       Number o = (Number) listNumber.get(0);

    }

    public static void reverse(List<?> list, String s) {
        rev(list);
    }

    private static <T> void rev(List<T> list) {
        List<T> tmp = new ArrayList<T>(list);
        for (int i = 0; i < list.size(); i++) {
            list.set(i, tmp.get(list.size()-i-1));
        }
    }

    public static void reverse(List<? super Object> list) {
        List<Object> tmp = new ArrayList<>(list);
        for (int i = 0; i < list.size(); i++) {
            list.set(i, tmp.get(list.size()-i-1)); // compile-time error
        }
    }

    public static Number getFirst(List<? extends Number> list) {
        return list.get(0); // compile-time error
    }

}




