package tests.core.abstractclass;

/**
 * Created by pom on 15.04.2018.
 */
public class StateTest3 {

    public static void main(String[] args) {
        Base derived = new Derived();
        ((Base)derived).foo();
    }
}

class Base {
    int myValue = 2;
   Base(int i) {
        this.f2();
       System.out.println("B="+myValue);
   }
    void foo() {
       System.out.println("Base="+myValue);
   }

   void f2() {
       this.foo();
   }
}

 class Derived extends Base {

    public Derived() {
       super(2);
       System.out.println("D="+myValue);
    }


    void foo() {
         myValue = 3;
        System.out.println("Derived="+myValue);
    }
}