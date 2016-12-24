package tests;

import javax.print.attribute.standard.MediaSize;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Max on 21.12.2016.
 */
public class ArrayListExample {

    static public void main (String [] args ) {
        test002();
    }



    static public void addElement(){
        ArrayList arrayList = new ArrayList();
        arrayList.add(null);
    }


    static public void teatAddElement(){
    //    ArrayList<Type1> type1s=factoryMethod ();
   //     System.out.println("type1s="+type1s);
       // type1s.add(new Type1("N1"));
      //  type1s.add(new Type2());
    }


    private static void test002() {
        List tabs = new ArrayList<>();
        ArrayList<Type1> type1s=factoryMethod (tabs);

      //  for (List t: tabs)

    }


    static public <cL> ArrayList<cL> factoryMethod (List tabs ) {

        ArrayList<cL> result = new ArrayList<>();
        tabs.add(result);
        return result;
    }

}


class Type1 {
    public String name;

    public Type1(String name) {this.name= name;}


}

class Type2 {


}