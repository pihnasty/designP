package tests.verticaTest;

import java.io.*;

public class showTrustStore {

    public static void main(String[] args) {
        String s =  System.getProperty("Javax.net.ssl.trustStore");

        if (s != null){
            System.out.println("Местоположение:   Javax.net.ssl.trustStore = " + s);
            return;
        }

        String home =  System.getProperty("Java.home");

        String p1 = home + File.separator +
                "lib"+File.separator+"security"
                +File.separator+"jssecacerts";

        if ( new File (p1).exists()){
            System.out.println("Местоположение: "+p1);
            return;
        }

        String p2 = home + File.separator +
                "lib"+File.separator+"security"
                +File.separator+"cacerts";

        if ( new File (p2).exists()){
            System.out.println("Местоположение: "+p2);
            return;
        }

        System.out.println("хранилище не найдено");
    }
}