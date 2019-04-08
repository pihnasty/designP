import java.util.Random;

public class RandomP {
    public static void main(String[] args) {
        Random random = new Random(10);
        for (int i=1; i<10; i++) {
            int randomNumber = random.nextInt(50);
            System.out.println(randomNumber);
        }
    }

}
