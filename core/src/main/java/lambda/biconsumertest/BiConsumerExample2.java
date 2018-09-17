package lambda.biconsumertest;

import java.util.function.BiConsumer;

/**
 * @author imssbora
 */
public class BiConsumerExample2 {
  public static void main(String[] args) {
    BiConsumer<Integer, Integer> addition = (a, b) -> {
      System.out.println(a + b);
    };

    BiConsumer<Integer, Integer> subtraction = (a, b) -> {
      System.out.println(a - b);
    };
    // Using andThen()
    addition.andThen(subtraction).accept(10, 6);

      }
}