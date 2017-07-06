package tests.core.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Pihnastyi.O on 7/4/2017.
 */
public class ArrayListForTeradata {
    public static void main(String[] args) {

  //      List<List<String>> unSortedData = new ArrayList();

        List<String [] > unSortedData2 = new ArrayList();
        String [] mas11 =  {"0","1","2","3","4"};
        String [] mas21 =  {"10","11","12","13","14"};

        unSortedData2.add(mas11);
        unSortedData2.add(mas21);
        System.out.println(unSortedData2.get(1)[1]);


        List< List<String> > unSortedData = new ArrayList();

        String [] mas1 =  {"0","1","2","3","4"};
        String [] mas2 =  {"10","11","12","13","14"};

        List<String> am1 = Arrays.asList(mas1);
        List<String> am2 = Arrays.asList(mas2);


        String [] masN2 = new String [5];



        unSortedData.add(am1);
        unSortedData.add(am2);

        System.out.println(unSortedData.get(1).get(1));


    }




}
