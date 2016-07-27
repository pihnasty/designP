package brus.brus_850_Annotation;


import org.testng.annotations.Test;

/**
 * Created by Pihnastyi.O on 7/27/2016.
 */
public class b850_Testable {
    public void execute(){
        System.out.println("Executing...");
    }
    @Test
    public void testExecute (){
        execute();
    }
}
