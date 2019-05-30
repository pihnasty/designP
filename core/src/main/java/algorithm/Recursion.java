package algorithm;

import java.util.ArrayList;
import java.util.List;

public class Recursion {
    public static void main(String[] args) {
        int n = 2;
        int N = 10;
        List<List<Long>> reverseSample = new ArrayList<>();

        Recursion recursion = new Recursion();

        long startTime = System.currentTimeMillis();
//        factors.forEach(
//                factor -> System.out.println(factor)
//        );

        recursion.forDynamyc (N, n, 0, reverseSample, null);

        long finishTime = System.currentTimeMillis();

        System.out.printf("  %d   %d   %d   %d", N,n,reverseSample.size(),(finishTime-startTime)/1000);
        System.out.println();

        List<List<Long>> sample  =  recursion.reverse (N, reverseSample);

        System.out.printf("  %d   %d   %d   %d", N,n,sample.size(),(finishTime-startTime)/1000);
        System.out.println();

        sample.forEach(
                row-> System.out.println(row)
        );

    }


    public void forDynamyc(int N, int n, int k, List<List<Long>> sample, List<Long> row) {
        for (long i = k; i < N; i++) {
            if (k == 0) {
                System.out.println(i);
                row = new ArrayList<>();
                for (int i2 = 0; i2 < n; i2++) {
                    row.add((long) -1);
                }
            }
            if (k == 0 || i > row.get(k - 1)) {
                row.set(k, i);
                if (k < n - 1) {
                    forDynamyc(N, n, k + 1, sample, row);
                } else {
                    List<Long> cloneRow = new ArrayList<>();
                    for (int iClone = 0; iClone < n; iClone++) {
                        cloneRow.add(row.get(iClone));
                    }
                    sample.add(cloneRow);
                }
            }
        }
    }

    public List<List<Long>> reverse (int N, List<List<Long>> reverseFactorNumbersList) {
        List<List<Long>>  factorNumbersList = new ArrayList<>();
        List<Long> fullFactorNumbersRow = new ArrayList<>();
        for (int i=0; i<N; i++) {
            fullFactorNumbersRow .add((long) i);
        }
        reverseFactorNumbersList.forEach(
                reverseFactorNumbersRow -> {
                    List<Long> factorNumbersRow = (List<Long>) ((ArrayList<Long>) fullFactorNumbersRow).clone();
                    factorNumbersRow.removeAll(reverseFactorNumbersRow);
                    factorNumbersList.add(factorNumbersRow);
                }
        );

        return factorNumbersList;

    }
}
