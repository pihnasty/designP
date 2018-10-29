package utilp.arraylist;

import java.util.ArrayList;
import java.util.List;

public class ArrayListP {

    public static void main(String[] args) {
        ArrayListP arrayListP = new ArrayListP();


    //    System.out.println( arrayListP.isDataAnchoredToRow ( ) );

        arrayListP.case02();

    }

    private Boolean isDataAnchoredToRow ( ) {

        List<String> values = new ArrayList<String>(){
            {
                add("Y");
                add("Yes");
                add("y");
                add("yes");
            }
        };
        return values.contains(null);
    }

    private void case02() {
        List<String> myList = new ArrayList<String>();
        myList.add("Apple");
        myList.add("Banana");
        myList.add("Orange");
        String[] myArray = myList.stream().toArray(String[]::new);
        System.out.println();
    }

}
