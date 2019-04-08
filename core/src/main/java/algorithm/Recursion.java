package algorithm;

import java.util.ArrayList;
import java.util.List;

public class Recursion {
    public static void main(String[] args) {
        int n = 2;
        int N = 40;
        List<List<Long>> sample = new ArrayList<>();

        Recursion recursion = new Recursion();

        long startTime = System.currentTimeMillis();
//        factors.forEach(
//                factor -> System.out.println(factor)
//        );

        recursion.forDynamyc (N, n, 0, sample, null);

        long finishTime = System.currentTimeMillis();

        System.out.printf("  %d   %d   %d   %d", N,n,sample.size(),(finishTime-startTime)/1000);



    }


    public void forDynamyc(int N, int n, int k, List<List<Long>> sample, List<Long> row) {
        for (long i = k; i < N; i++) {
            if (k == 0) {
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
                    sample.add(row);
                    row = new ArrayList<>();
                    for (int i2 = 0; i2 < n; i2++) {
                        row.add(sample.get(sample.size() - 1).get(i2));
                    }
                }
            }
        }
    }
}
