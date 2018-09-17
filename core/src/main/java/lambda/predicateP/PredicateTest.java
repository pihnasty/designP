package lambda.predicateP;

import java.util.Objects;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class PredicateTest {
    public static void main(String[] args) {
        Predicate<String> predicate = (s) -> s.length() > 0;



        predicate.test("foo");              // true  ++  Ветка з2
        predicate.negate().test("foo");     // false

        Predicate<Boolean> nonNull = Objects::nonNull;
        Predicate<Boolean> isNull = Objects::isNull;

        Predicate<String> isEmpty = String::isEmpty;
        Predicate<String> isNotEmpty = isEmpty.negate();

        Predicate isPrintComma2 = new Predicate() {
            boolean isPrint = false;
            @Override
            public boolean test(Object iSprinted) {
                boolean isPrintBefore = isPrint;
                isPrint =  (iSprinted).equals(false) ? true : true;
                return isPrintBefore;
            }
            public Boolean getPrint() {
                return isPrint;
            }
        };


    //    Predicate<Boolean> isPrintComma2 =    iSprinted -> iSprinted.equals(false) ? !iSprinted : iSprinted;

        Boolean is = false;
        isPrintComma2.test(is);
        isPrintComma2.test(is);
        isPrintComma2.test(is);
        isPrintComma2.test(is);
        isPrintComma2.test(is);


        Supplier <Boolean>  isPrintComma = new Supplier<Boolean>() {
            boolean isPrint = false;
            @Override
            public Boolean get() {
                boolean isPrintBefore = isPrint;
                if (!isPrint) {
                    isPrint = !isPrint;
                }
                return isPrintBefore;
            }
        };

        boolean a = isPrintComma.get();
        a = isPrintComma.get();
        a = isPrintComma.get();
        a = isPrintComma.get();
        a = isPrintComma.get();
        a = isPrintComma.get();





    }
}
