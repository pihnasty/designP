package tests.lambda;

import java.util.function.BiFunction;
import java.util.function.DoubleFunction;

/**
 * Created by pom on 26.03.2017.
 */
public class Lambda {
    public static void main(String[] args) {
        DoubleFunction<Double> function = x -> x;
        double x0=2;
        System.out.println("function("+x0+")="+function.apply(x0));

        double result = MathP.integralF.apply  (0,1,function);

        System.out.println("result="+result);


        result = MathP.integrationParameter.apply  (0,2.5, 0.5, function);

        System.out.println("result="+result);

        System.out.println("h="+MathP.h(-1.0));


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