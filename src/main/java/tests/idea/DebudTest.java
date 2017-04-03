package tests.idea;

import java.util.Scanner;

/**
 * Created by Pihnastyi.O on 4/3/2017.
 */

/*В режиме Debug я могу делать изменения в class Update, и изменения будут подхватываться.*/
public class DebudTest {
    public static void main(String[] args) {

        int i = 3;

        UpdatedClass up = new UpdatedClass();

        Scanner in = new Scanner(System.in);

        while (true) {
            up.update(i+1);
            i = in.nextInt();

        }


    }
}
