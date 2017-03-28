package tests.nestedClasses;

/**
 * Created by pom on 25.03.2017.
 */
public class NestedClasses02 {
    public static void main(String[] args) {

        Math.Factorial fact = Math.getFactorial(6);

        Math.Factorial fact2 = new Math.Factorial(1,3);

        System.out.printf("Факториал числа %d равен %d \n", fact.getKey(), fact.getResult());
    }
}

class Math{

    public static class Factorial{

        private int result;
        private int key;

        public Factorial(int number, int x){

            result=number;
            key = x;
        }

        public int getResult(){
            return result;
        }

        public int getKey(){
            return key;
        }
    }

    public static Factorial getFactorial(int x){

        int result=1;
        for (int i = 1; i <= x; i++){

            result *= i;
        }
        return new Factorial(result, x);
    }
}