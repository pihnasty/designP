package brus.TIJ4.innerclasses;

class D {
}

abstract class E {
    int getIntE() {
        return 1;
    }

    String getStringE() {
        System.out.println("getStringE()");
        return "getStringE()";
    }
    abstract void hello() ;
}

class Z extends D {
    private int a = 2;
    E makeE() {
        return new E() {
            @Override
            void hello() {
                System.out.println("a="+a);
            }
        };
    }
}

public class MultiImplementation {
    static void takesD(D d) {
    }

    static void takesE(E e) {
        e.getStringE();
        e.hello();
    }

    public static void main(String[] args) {
        Z z = new Z();
        takesD(z);
        takesE(z.makeE());
    }
} ///:~
