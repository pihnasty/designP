package aws.oop;

public class Poly {
    public static void main(String[] args) {
        A a = new A();
        a.show();

        A a1 = new B1();
        a1.show();

        A a2 = new B2();
        a2.show();


    }
}

class A {
    public void show() {
        System.out.println("A");
    }
}

class B1 extends A {

    @Override
    public void show() {
        System.out.println("B1");
    }
}

class B2 extends A {

    @Override
    public void show() {
        System.out.println("B2");
    }
}