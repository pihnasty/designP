package ib_task;

public class TaskIB_2_2 {
    public static void main(String[] args) {

        int x = 1;
        for (int i = 1; i < 100; i++) {

            x = x * 6;

        }




        double Sum = 1.0 / 500_000.0 / 500_000.0;

        int count = 6;
        int n= 2;

        for (int i1 = 1; i1 < count; i1++) {

            Sum = Sum * 6;
        }


        double [] value  = new double [2*count*n+1] ;


        System.out.println(Sum);

        for (int i1 = 1; i1 <= count; i1++) {

            for (int i2 = 1; i2 <= count; i2++) {



                                System.out.println(String.format("     %8d  %8d ", i1, i2));
                                value[i1 + i2] = value[i1 + i2 ] + 1;
            }

        }


        for (int i1 = n ; i1 <= 2*count*n; i1++) {
            System.out.println(String.format("   %8d    %8.0f    %8.0f    ", i1, value [i1],  value [i1]/i1));
        }

        Sum=0;
        for (int i1 = n ; i1 <= 2*count*n; i1++) {
            Sum = Sum+value [i1];
            System.out.println(String.format("   %8d    %8.0f       ", i1, value [i1]));
        }

        System.out.println("sum="+Sum+"   "+6*6);

        double provSum = 1.0;

        for (int i1=1; i1<=n; i1++) {
            provSum=provSum*6.0;
        }

//        System.out.println(provSum + "sm="+Sum+ "    "  + value[7*taked/2]+ "   "+  value[7*taked/2]/Sum);







/*

        double sigma1 = (6.0-1.0)/2.0/Math.sqrt(3.0);

        double sigmaN = sigma1/1000.0;

        double eps = 0.000_001;

        double r = eps/sigmaN;

        System.out.println("sigma_1="+sigma1+"               sigma_N="+  sigmaN +" r=  "+ r );




        double probably = 2* intLaplaca(r);

        System.out.println("Вероятность="+probably);

*/

    }

    static  double factorial (double d) {
        double sum =1;

        for (int i1=1; i1<=d; i1++) {
            sum=sum*i1;
        }

        return sum;
    }

    static double c (double k, double n) {
        return factorial(n)/factorial(k)/factorial(n-k);
    }

    static double intLaplaca(double x) {
        double integral = 1.0/Math.sqrt(2.0*Math.PI)*(x
            -x*x*x/6
        );
        return integral;
    }



}
