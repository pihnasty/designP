package tests.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.DoubleFunction;

/**
 * Created by pom on 26.03.2017.
 */
public class Lambda {
    public static void main(String[] args) {
//        DoubleFunction<Double> function = x -> x;
//        double x0=2;
//        System.out.println("function("+x0+")="+function.apply(x0));
//
//        double result = MathP.integralF.apply  (0,1,function);
//
//        System.out.println("result="+result);
//
//
//        result = MathP.integrationParameter.apply  (0,2.5, 0.5, function);
//
//        System.out.println("result="+result);
//
//        System.out.println("h="+MathP.h(-1.0));


        ControlSpeedBand controlSpeedBand = new ControlSpeedBand();

        System.out.println("controlSpeedBand.optimalControlSpeedBand="+controlSpeedBand.optimalControlSpeedBand(0.76));

        // controlSpeedBand.example1();

    }


}


class ControlSpeedBand {

    private  double [] cascadeControlSpeedBand ={0.33, 0.5, 1.0};
    public  double optimalControlSpeedBand(Double controlSpeedBand) {
        class Result {
            double optimalControlSpeedBandResult = 0.0;
            double delta = Double.MAX_VALUE;
            List<Double> mas = new ArrayList<>();
            public Result(double [] doubleMas) {
                for( Double m : cascadeControlSpeedBand ) {
                    mas.add(m);
                }
            }
        }
        Result r = new Result(cascadeControlSpeedBand);

        r.mas.stream().forEach( speed -> {
                    if (Math.abs(speed - controlSpeedBand) < r.delta) {
                        r.delta = Math.abs(speed - controlSpeedBand);
                        r.optimalControlSpeedBandResult=speed;
                    }
                }
        );
        return r.optimalControlSpeedBandResult;
    }

    public void example1(){
        List<String> items = new ArrayList<>();
        items.add("A");
        items.add("B");
        items.add("C");
        items.add("D");
        items.add("E");

//lambda
//Output : A,B,C,D,E
        items.forEach(item->System.out.println(item));

//Output : C
//        items.forEach(item->{
//            if("C".equals(item)){
//                System.out.println(item);
//            }
//        });
    }

}


class MathP {

    static final double NUMBER_AXIS_PARTITION = 10000;

    static double h (double x) {
        return ( x==0.0) ? 0.5 : ((x>0) ? 1.0 : 0.0 );
    }
    static ThreeDoubleFunction integralF = (t0, tK, function) -> {
        double dt = (tK-t0)/NUMBER_AXIS_PARTITION;
        double result = 0.0;
        for (double t=t0; t<tK; t+=dt ) {
            result = result + function.apply(t)*dt;
        }
        return result;
    };

    static FourDoubleFunction integrationParameter =
            (t0, tK, C1, function) -> {
                if (C1 <= 0) return t0;

                double dt = (tK-t0)/NUMBER_AXIS_PARTITION;
                double result = 0.0;
                double tP = t0;

                while (result < C1) {
                    result = integralF.apply(t0, tP, function);
                    tP+=dt;
                }
                return tP;
            };


    interface ThreeDoubleFunction {
        Double apply(double t0, double tK, DoubleFunction<Double> function);
    }

    interface FourDoubleFunction {
        Double apply(double t0, double tK, double С1, DoubleFunction<Double> function);
    }




}

