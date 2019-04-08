package algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Recursion {
    public static void main(String[] args) {
        List<String> factors = new ArrayList(Arrays.asList("1","2","3","4","5","6","7","8","9"));
        int n = 2;
        List<List<String>> sample = new ArrayList();

        Recursion recursion = new Recursion();


        factors.forEach(
                factor -> System.out.println(factor)
        );

        recursion.forDynamyc (n, factors, sample, true, null);

        System.out.println("    ");

    }



    public void forDynamyc (int n, List<String> factors, List sample, boolean isAddedRow, List<String> row) {


            for (int i = 1; i<n; i++) {
                if (isAddedRow) {
                     row = new ArrayList<>();
                }

                row.add(factors.get(i));

                if (n>0) {
                    System.out.println("->"+n);
                    forDynamyc(n-1,factors, sample, false, row);
                }

                if (isAddedRow) {
                    sample.add(row);
                }
            }



        System.out.println("<-"+n);
    }

}
