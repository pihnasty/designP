package ib_task;

public class TaskIB_2 {
    public static void main(String[] args) {

        int x = 1;
        for (int i = 1; i < 100; i++) {

            x = x * 6;

        }

        x = 3500 * 6;


        double Sum = 1.0 / 500_000.0 / 500_000.0;

        int count = 6;
        int n= 6;

        for (int i1 = 1; i1 < count; i1++) {

            Sum = Sum * 6;
        }


        double [] value  = new double [2*count*n+1] ;


        System.out.println(Sum);

        for (int i1 = 1; i1 <= count; i1++) {

            for (int i2 = 1; i2 <= count; i2++) {
                for (int i3 = 1; i3 <= count; i3++) {
                    for (int i4 = 1; i4 <= count; i4++) {
                        for (int i5 = 1; i5 <= count; i5++) {
                            for (int i6 = 1; i6 <= count; i6++) {


                                System.out.println(String.format("     %8d  %8d  %8d  %8d  %8d  %8d", i1, i2, i3, i4, i5, i6));
                                value[i1 + i2 + i3 + i4 +i5+i6] = value[i1 + i2 + i3 + i4+i5+i6] + 1;
                            }
                        }


                    }
                }
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

        System.out.println("sum="+Sum+"   "+6*6*6*6*6*6);

        double provSum = 1.0;

        for (int i1=1; i1<=n; i1++) {
            provSum=provSum*6.0;
        }

//        System.out.println(provSum + "sm="+Sum+ "    "  + value[7*taked/2]+ "   "+  value[7*taked/2]/Sum);


        double timeSecund = 500_000.0*500_000.0*625_000.0*625_000.0*833_333.0*833_333.0/1_000_000_000.0;
        System.out.println("Сложность алгоритма="+ timeSecund);

        double timeHour = timeSecund/3600;
        System.out.println("Сложность алгоритма timeHour="+ timeHour);

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
