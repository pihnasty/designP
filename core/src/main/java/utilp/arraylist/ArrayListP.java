package utilp.arraylist;

import java.util.ArrayList;
import java.util.List;

public class ArrayListP {

    public static void main(String[] args) {
        ArrayListP arrayListP = new ArrayListP();


        System.out.println( arrayListP.isDataAnchoredToRow ( ) );
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
}
