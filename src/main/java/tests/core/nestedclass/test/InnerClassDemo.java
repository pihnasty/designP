package tests.core.nestedclass.test;

class Outer {
    int outer_x = 100;
    void test () {
        for (int i = 0 ; i<10; i++ ) {
            class Inner {
                void display () {
                    System.out.println("Printed outer_x="+outer_x);
                }
            }
            Inner inner = new Inner();
            inner.display();
        }
    }
    public class InnerInOuterClass {
        int innerInOuterClass_x = 2001;
    }
    class InnerInOuterClass2 {
        int innerInOuterClass_x = 2001;
    }
}


public class InnerClassDemo {
    public static void main(String[] args) {
        Outer outer = new Outer();
        outer.test();

        Outer.InnerInOuterClass innerInOuterClass = new Outer().new InnerInOuterClass();
        System.out.println("innerInOuterClass.innerInOuterClass_x="+innerInOuterClass.innerInOuterClass_x);

        Outer.InnerInOuterClass2 innerInOuterClass2 = new Outer().new InnerInOuterClass2();
    }
}