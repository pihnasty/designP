package tests.core.list;


import javafx.util.Pair;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Pihnastyi.O on 3/28/2017.
 */
public class CashIntegral {
    public static void main(String[] args) {

        //Pair p =new Pair();



        double t0 = 0;
        double tK = 10;

        int n = 10000;
        double dt = (tK - t0) / n;

        Random random = new Random();






        CashIntegralValue cashIntegralValue = new CashIntegralValue();

        for (double t = 0; t < tK; t += dt) {
            cashIntegralValue.addValue(t, random.nextDouble() * 100);
        }

        cashIntegralValue.sorted();




        for (int i = 0; i < n-1; i++)
            System.out.println(cashIntegralValue.getKey(i) + "   " + cashIntegralValue.getValue(i));


        for (double C1= 10; C1<100; C1+=1.0 ) {

            double tZ = cashIntegralValue.getFromCacheKey(C1);
            System.out.println("t="+tZ+ "C1="+C1);

        }


    }

}

/*  The solution of equation t=C1(t) */
class CashIntegralValue {
    private List<Pair<Double, Double>> integralCashSorted;
    private double C1max = Double.MIN_VALUE;
    private double C1min = Double.MAX_VALUE;


    public CashIntegralValue() {
        integralCashSorted = new ArrayList<>();
    }

    public void addValue(double t, double C1) {
        integralCashSorted.add(new Pair<>(t, C1));
    }

    public void sorted() {
        integralCashSorted = integralCashSorted.stream().sorted((p1, p2) -> (p2.getValue() > p1.getValue()) ? -1 : 1)
                .collect(Collectors.toList());
        if (!integralCashSorted.isEmpty()) {
            C1max = integralCashSorted.get(integralCashSorted.size()-1).getValue();
            C1min = integralCashSorted.get(0).getValue();
        }
    }

    public double getFromCacheKey(double C1) {
         try {
             if (C1 > C1max) throw new Exception("-------------------- C1 > C1max ---------------------  C1="+C1+"  C1Max="+C1max);
             if (C1 < C1min) throw new Exception("-------------------- C1 < C1min ---------------------  C1="+C1+"  C1Max="+C1min);
        } catch (Exception e) {  e.printStackTrace();    }
        return integralCashSorted.stream().filter(p -> p.getValue() > C1).findFirst().get().getKey();
    }

    public double getKey(int i) {
        return integralCashSorted.get(i).getKey();
    }

    public double getValue(int i) {
        return integralCashSorted.get(i).getValue();
    }

}