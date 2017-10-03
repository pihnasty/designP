package tests.core.privateProtectedPublic.package1;

/**
 * Created by Pihnastyi.O on 10/3/2017.
 */
public class A2 extends A1 {
   public A2() {
       super.show();
       this.show();
   }

    public static void main(String[] args) {
        A2 a2 = new A2();
    }
}


