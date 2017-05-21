package shild.chapter14._21;

// Generics and arrays.

class A<V> extends Number {

    @Override
    public int intValue() {
        return 0;
    }

    @Override
    public long longValue() {
        return 0;
    }

    @Override
    public float floatValue() {
        return 0;
    }

    @Override
    public double doubleValue() {
        return 0;
    }
}

class Gen<T extends Number> {
    T ob;

    T vals[]; // OK

    Gen(T o, T[] nums) {
        ob = o;

        // This statement is illegal.
//         vals = new T[10]; // can't create an array of T

        // But, this statement is OK.
        vals = nums; // OK to assign reference to existent array
    }
}

class GenArrays {
    public static void main(String args[]) {
        Integer n[] = { 1, 2, 3, 4, 5 };

        Gen<Integer> iOb = new Gen<Integer>(50, n);

        // Can't create an array of type-specific generic references.
     //    Gen<Integer> gens[] = new Gen<Integer>[10]; // Wrong!

        // This is OK.
     //   Gen< A <Double>> gens2[] = new Gen< A <Double>>[10]; // OK

        Gen<?> gens[] = new Gen<?>[10];
        gens[0] = new Gen<Integer>(1, new Integer[]{1});

        Gen<Number> gens3[] = (Gen<Number>[]) new Gen<?>[10];

    }
}
