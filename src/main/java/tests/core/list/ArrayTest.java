package tests.core.list;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Pihnastyi.O on 12/20/2016.
 */
public class ArrayTest {
        static public void main (String [] args ) {

            //_isEmpty();
            //searchElement();
            containsElements();
        }

        private static void _isEmpty() {

            String [] row = {""};

            if (row.length ==1) {
                System.out.println("1");
            }

            if (row.length ==1 && row[0].isEmpty()) {
                System.out.println("1+1");
            }


        }

        private static void searchElement(){
          getUsers().contains("IVANOV");

          List<String> checkedUsers = getUsers().stream().filter(user->

                  (user.toUpperCase()).equals("PeTrOv".toUpperCase())

          ).collect(Collectors.toList());
            System.out.println("checkedUsers="+checkedUsers);


//            for (String user: getUsers() ) {
//
//              if   ( user.toUpperCase())==("PETROv".toUpperCase()) {
//
//                }
//
//
//            }



        }

    public static List<String> getUsers()  {
        List<String> list=  Arrays.asList("IVANOV","PETROV","SidoRov");
        return list;
    }
        enum A { A1, A2, A3, A4 }
        public static void containsElements() {

            A b = A.A1;

            List <A> vendorForwardOnly = Arrays.asList(A.A1);

            System.out.println();
            int resultType = vendorForwardOnly.contains(b) ? 2 : 1;
            System.out.println("Result="+resultType);


        }






    }




