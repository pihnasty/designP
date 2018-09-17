package lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class SkipTest {
  public static void main(String[] args) {

    List<Integer> numbers = new ArrayList<>();
    numbers.add(1);
    numbers.add(2);
    numbers.add(3);
    numbers.add(4);
    numbers.add(5);
    numbers.add(6);

    Stream<Integer> stream1 = numbers.stream();
    // Limit - return new stream of 3 elements
    System.out.println("--------Stream elements after limiting----------");
    stream1.limit(3).forEach((a) -> {
      System.out.println(a);
    });

    Stream<Integer> stream2 = numbers.stream();
    // Skip - return new stream of remaining elements
    // after skipping first 2 elements
    System.out.println("--------Stream elements after skipping----------");
    stream2.skip(-1).forEach((a) -> {
      System.out.println(a);
    });
  }
}