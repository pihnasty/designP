package tests.core.privateProtectedPublic.package2;

import tests.core.privateProtectedPublic.package1.A1;

/**
 * Created by Pihnastyi.O on 10/3/2017.
 */
public class A3 extends A1 {
   public A3() throws CloneNotSupportedException {
       super.show();
       this.show();
   }

    public static void main(String[] args) throws CloneNotSupportedException {
        A3 a3 = new A3();
        a3.show();
    }
}
