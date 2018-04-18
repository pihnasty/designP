package tests.core.abstractclass;

/**
 * Created by pom on 15.04.2018.
 */
public class StateTest2 {

    public static void main(String[] args) {
        D d = new D();
    }
}

 abstract class G{
     int myValue = 2;
    G(int _myValue) {
        System.out.println(myValue);
        myValue =_myValue;
        System.out.println(myValue);
    }


    }

 class D extends G {

    public D() {
        super(3);
        System.out.println(myValue);
    }
}