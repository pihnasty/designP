package tests.core.abstractclass;

import java.util.ArrayList;

/**
 * Created by pom on 15.04.2018.
 */
public class StateTest {

    public static void main(String[] args) {
        A a = new B();
        B b1 = new B();
        a.getI();
        b1.getI2();
        System.out.println("a.getI()="+a.getI());
        a.set(3);
        System.out.println("a.getI()="+a.getI());


        Fimpl fimpl = new Fimpl();
        System.out.println("fimpl="+fimpl.getI());
        fimpl.setI(6);
        System.out.println("fimpl="+fimpl.getI());
        fimpl.setI(7);
        System.out.println("fimpl="+fimpl.getI());

        Fimpl2 fimpl2 = new Fimpl2();
        System.out.println("fimpl="+fimpl.getI());


        // a.getClass().getFields()

    }
}


class Fimpl implements F {

}

class Fimpl2 implements F {

}



abstract class A {
    public int i;
    public A (int i) {
        this.i=i;
    }

    protected A() {
    }

    public int getI () {
        return i;
    }
    void set(int i) {
        this.i=i;
    }
}

class B extends A {
    private int i2=1;
    public B() {
        super(2);
    }

    public int getI2() {
        return i2;
    }
}

interface F {
  ArrayList<Integer> i = new ArrayList<>();

  default void setI(int _i) {
      if (i.size()>0) i.add(0,_i);
      i.add(_i);
  }

    default int getI() {
        return i.size()==0?0:i.get(0);
    }
}



