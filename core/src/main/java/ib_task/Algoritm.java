package ib_task;

public class Algoritm {

    public static void main(String[] args) {
        double c1 = 500_000;
        double c2 = 625_000;
        double c3 = 833_333;

        double countOpetation = c1*c2*c3;
        System.out.println("Сложность алгоритма="+countOpetation);

        double timeSec = countOpetation/1000000000.0;
        System.out.println("Время, секунд="+timeSec);

        double timeHour = timeSec/3600;
        System.out.println("Время, час="+timeHour);

        double timeDay = timeHour/24;
        System.out.println("Время, дней="+timeDay);

        double timeYear = timeDay/365.25;
        System.out.println("Время, лет="+timeYear);


        System.out.println(2500_000/4.0);


        System.out.println("a1="+(500000*6+500000*1));   //  6-1

        System.out.println("a2="+(625000*5+375000)+"   "+(625000+375000));     //  5-1

        System.out.println("a3="+(833_333*4+166_666));   //  4-1

        System.out.println("a3="+(833_333*3+6*166_666));   //  4-1


//        for (int i1 = 1; i1 <= c1; i1++) {
//
//            for (int i2 = 1; i1 <= c2; i2++) {
//                for (int i3 = 1; i3 <= c3; i3++) {
//
//
//                    System.out.println(String.format("       %8d  %8d  %8d", i1, i2, i3));
//
//                }
//
//            }
//
//
//        }
    }

}
