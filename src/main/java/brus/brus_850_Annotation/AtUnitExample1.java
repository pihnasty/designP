package brus.brus_850_Annotation;

import org.junit.Test;

/**
 * Created by Pihnastyi.O on 5/6/2016.
 */
public class AtUnitExample1 {
    public String methodOne() {
        return "This is methodOne";
    }
    public int methodTwo() {
        System.out.println("This is methodTwo");
        return 2;
    }
    @Test public void methodOneTest () {  methodOne().equals("This is methodOne");}


}
