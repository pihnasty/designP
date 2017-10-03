package tests.FuntionalInterface.supper;

import java.util.function.Supplier;

/**
 * Created by Pihnastyi.O on 9/28/2017.
 */
public class  SupplierP {
    public static void main(String[] args) {
        Supplier supplier = new Supplier() {
            private int i =0;
            @Override
            public Integer get() {
                return i++;
            }
        };

        System.out.println( supplier.get()  );
        System.out.println( supplier.get()  );



    }
}
