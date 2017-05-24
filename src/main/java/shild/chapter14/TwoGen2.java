package shild.chapter14;

// A simple generic class with two type
// parameters: T and V.
class TwoGen2<T, V> {
    T ob1;
    V ob2 = null;

    // Pass the constructor a reference to
    // an object of type T.
    TwoGen2(T o1) {
        ob1 = o1;

    }

    // Show types of T and V.
    void showTypes() {
        System.out.println("Type of T is " +
                ob1.getClass().getName());

        System.out.println("Type of V is " +
                ob2.getClass().getName());
    }

    T getob1() {
        return ob1;
    }

    V getob2() {
        return ob2;
    }

    void setob2(V ob2) {
        this.ob2=ob2;
    }

}
