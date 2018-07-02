package tests.core.optinal.test;

import java.util.Optional;

public class OptinalTest{
    public static void main(String[] args) {

//        OptinalTest optinalTest =  new OptinalTest();
//        System.out.println( optinalTest.checkOptional("d") );
//        System.out.println( optinalTest.checkOptional("") );
//        Object o = null;
//        System.out.println( optinalTest.checkOptional(o) );

        String s = "";
        Optional optional = Optional.ofNullable(s.isEmpty());
        System.out.println( optional.isPresent() );



    }

    public <T> boolean checkOptional (T o) {
               return Optional.of(o).isPresent();
    }




}
