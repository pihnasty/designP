package pattern;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Locale;

class Product { }

class ConcreteProductA extends Product { }

class ConcreteProductB extends Product { }

abstract class Creator {
    public abstract Product factoryMethod();
}

class  ConcreteCreatorA extends Creator {
    @Override
    public Product factoryMethod() { return new ConcreteProductA(); }
}

class ConcreteCreatorB extends Creator {
    @Override
    public Product factoryMethod() { return new ConcreteProductB(); }
}

public class FactoryMethodExample {
    public static void main(String[] args) {
        // an array of creators
        Creator[] creators = {new ConcreteCreatorA(), new ConcreteCreatorB()};
        // iterate over creators and create products
        for (Creator creator: creators) {
            Product product = creator.factoryMethod();
            System.out.printf("Created {%s}\n", product.getClass());
        }
    }
}

class StringExampleFactoryMethodJDK {
    /*
        P -> PA     ->  CA             -> C
        P -> String ->  String.valueOf -> -- valueOf(P)
    */
    public static void main(String[] args) {
        String str = "";
        str.valueOf("");
    }
}

class CollectionsExampleFactoryMethodJDK {
    /*
        P -> PA         ->  CA             -> C
        P -> Collection  ->  Collections.unmodifiableCollection(P) -> -- unmodifiableCollection(P)
    */
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();         list.add("B");        list.add("C");
        System.out.println("Initial list: "+ list);

        java.util.Collection immutablelist = Collections.unmodifiableCollection(list);

        //  try to modify the list
        //        immutablelist.add("D");
        Calendar.getInstance();
    }
}
class CalendarExampleFactoryMethodJDK {
    /*
        P       -> PA         ->  CA             -> C
        Locale  -> Calendar   ->  Calendar.getInstance(Locale) -> -- unmodifiableCollection(Locale)
    */
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
                 calendar = Calendar.getInstance(new Locale("en"));
    }
}


/*-- Another Examples in the JDK: ---------------------------------------------------------------------------------*/
/*



Factory methods are static methods that return an instance of the native class. Examples in the JDK:
        LogManager.getLogManager
        Pattern.compile


        getInstance() method which creates instance of Singleton class.

        newInstance() method which is used to create and return new instance from factory method every time called.

        getType() and newType() equivalent of getInstance() and newInstance() factory method but used when factory method resides in separate class.


        Read more: http://javarevisited.blogspot.com/2011/12/factory-design-pattern-java-example.html#ixzz4iAwHzqcN

*/

