package tests;

/**
 * Created by Pihnastyi.O on 12/20/2016.
 */
public class ArrayTest {
        static public void main (String [] args ) {
            _isEmpty();
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
    }
