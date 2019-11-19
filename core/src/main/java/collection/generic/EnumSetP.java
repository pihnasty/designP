package collection.generic;

import java.util.EnumSet;

public class EnumSetP {
    enum Day2 { MONDAY, TUESDAY, WEDNESDAY,
        THURSDAY, FRIDAY, SATURDAY, SUNDAY }
    public static void main(String[] args) {
        System.out.print("Weekdays: ");
        for (Day2 d : EnumSet.range(Day2.MONDAY, Day2.FRIDAY))
            System.out.print(d + " ");


    }
}

class E {

}


interface MyComparable<T> {
    int myCompare(T o);
}

class MyEnum<E extends MyEnum> implements MyComparable<E> {
    public int myCompare(E o) { return -1; }
}

class FirstEnum extends MyEnum<FirstEnum> {}

class SecondEnum extends MyEnum<SecondEnum> {}

class E2 <A extends Enum<A>> {



}

