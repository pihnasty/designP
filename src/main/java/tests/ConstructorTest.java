package tests;

/**
 * Created by pom on 04.03.2017.
 */
public class ConstructorTest {
}



class A {

    private final int a;

    public A(int a) {
         this.a = a;
    }
}

class B extends A {

    public B(int a) {
        super(a);
    }
}