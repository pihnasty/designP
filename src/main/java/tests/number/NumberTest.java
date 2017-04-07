package tests.number;






/**
 * Created by Pihnastyi.O on 4/5/2017.
 */
public class NumberTest {
    public static void main(String[] args) {
   //     NumberUtils.isDigits("1");
        Integer.getInteger("1");
        System.out.println(Character.isDigit('5'));

        System.out.println( "1.2".matches("\\d+"));


    }


    public boolean isNumeric(String s) {
        return s.matches("\\d+");
    }



}
