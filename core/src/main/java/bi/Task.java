package bi;

public class Task {

    public static void main(String[] args) {

        int Ns = 500000;

        int[] N = new int[7];
        N[0] = 0;
        N[1]=1;
        N[2]=1;
        N[3]= Ns-N[1]-N[2];

        N[4] = N[3];
        N[5] = N[2];
        N[6] = N[1];

        int sum = 0;

        for (int i=1; i<7; i++) {

            sum = sum + N[i]*i;

        }

        System.out.println(sum);


//
//        N[0] = 0;
//        N[1]=1;
//        N[2]=1;
//        N[3]= Ns-N[1]-N[2];
//
//        N[4] = N[3]+2;
//        N[5] = N[2]+1;
//        N[6] = N[1]-1;
//
//         sum = 0;
//
//        for (int i=1; i<7; i++) {
//
//            sum = sum + N[i]*i;
//
//        }
//
//        System.out.println(sum);


        double  d = 1;
        int f=1;
        int k = 100;

        for (int i=1; i<k; i++) {

            d = d * 6.0 / ((double)i);


        }

        System.out.println(k+"   "+d+"   "+f);

    }
}
