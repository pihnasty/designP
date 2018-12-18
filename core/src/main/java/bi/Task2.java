package bi;

public class Task2 {
    public static void main(String[] args) {
        double [] Nmax = new double[7];

        double n=1_000_000.0;
        double m0=3_500_000.0;
        double k=6;
        double m1=m0-n;
        for(int i=2; i<= k; i++) {
            int value = (int)m1/(i-1);
            Nmax[i]=value;
            System.out.println(Nmax[i]);
        }
        System.out.println("---------------------------------------");
        double [] nA = new double[7];
        for(int i=4; i<= k; i++) {
            nA[i]=n-Nmax[i]-1;
            System.out.println(nA[i]);
        }
        System.out.println("---------------------------------------");

        double Pwell = 1.0;
        for (int i=1; i<=2*k;i++) {
            Pwell=Pwell*n;
            System.out.println("="+Pwell);
        }

        double Pcommon=Pwell;
        for (int i=1; i<=10_000;i++) {
            Pcommon=Pcommon/6.0;
            System.out.println("i="+i+"  Pcommon="+Pcommon);
        }


        double n_6 = 2.0*Math.PI*n/6.0;
        double n_66 = n_6*n_6*n_6;
        double n6 = Math.sqrt(2.0*Math.PI*n);

        System.out.println("n6="+n6+"   n_6="+n_6+"   n_66="+n_66);
        System.out.println("n6/n_66="+n6/n_66);
        System.out.println("n_sum="+n6/n_66*n*n*n*n*n);

        System.out.println(6*n/Math.log(6*n));


        System.out.println(1*1+2*1+ 499995*3+(499995+8)*4);

    }

}
