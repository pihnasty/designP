package shild.chapter11._01;

import java.util.function.Supplier;

public class TheadWithOutRunable {
    public static void main(String[] args) {


        Supplier s = () -> {
            System.out.println("Hello Word");
            return true;
        };



        Thread t = new Thread(()->{});



    }
}

interface My {
        void execute ();
}
