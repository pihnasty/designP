package propertydemo;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class Main {

    public static void main(String[] args) {
/**********************************
 *      https://docs.oracle.com/javafx/2/binding/jfxpub-binding.htm
 */

 //       example2();

//       example3();

//        example4();

          example7();

    }

    private static void example7() {
        final DoubleProperty a = new SimpleDoubleProperty(1);
        final DoubleProperty b = new SimpleDoubleProperty(2);
        final DoubleProperty c = new SimpleDoubleProperty(3);
        final DoubleProperty d = new SimpleDoubleProperty(4);

        DoubleBinding db = new DoubleBinding() {

            {
                super.bind(a, b, c, d);
            }

            @Override
            protected double computeValue() {
                return (a.get() * b.get()) + (c.get() * d.get());
            }
        };

        System.out.println(db.get());
        b.set(3);
        System.out.println(db.get());
//        14.0
//        15.0
//
//        Process finished with exit code 0
    }

    private static void example4() {
        IntegerProperty num1 =null;
        num1 = new SimpleIntegerProperty(1);
        IntegerProperty num2 =null;
        num2 = new SimpleIntegerProperty(2);
        NumberBinding sum = Bindings.add(num1,num2);
        System.out.println("1="+sum.getValue());
        System.out.println("1=");
        num1.setValue(2);
        System.out.println("2="+sum.getValue());
        System.out.println("2=");

//        1=3
//        1=
//        2=4
//        2=
//        Process finished with exit code 0

    }

    private static void example3() {
        IntegerProperty num1 = new SimpleIntegerProperty(1);
        IntegerProperty num2 = new SimpleIntegerProperty(2);
        NumberBinding sum = num1.add(num2);
        System.out.println(sum.getValue());
        num1.set(2);
        System.out.println(sum.getValue());
//        3
//        4
//        Process finished with exit code 0
    }

    private static void example2() {
        Bill electricBill = new Bill();


        electricBill.amountDueProperty().addListener(new ChangeListener(){
            @Override public void changed(ObservableValue o, Object oldVal,
                                          Object newVal){
                System.out.println("Electric bill has changed!");
            }
        });

        electricBill.setAmountDue(100.00);

        electricBill.setAmountDue(200.00);

        electricBill.setAmountDue(200.00);
    }
}