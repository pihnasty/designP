package tests.core.privateProtectedPublic.package2;

import tests.core.privateProtectedPublic.package1.A1;
import tests.core.privateProtectedPublic.package1.PM;

/**
 * Created by Pihnastyi.O on 10/3/2017.
 */
public class A4 extends A1 implements PM {
   public A4() {
   }

    @Override
    public void show() throws CloneNotSupportedException {
        super.show();

    }
}
