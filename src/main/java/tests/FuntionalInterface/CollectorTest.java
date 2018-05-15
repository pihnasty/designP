package tests.FuntionalInterface;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectorTest {

  public static void main(String[] args) {
    CollectorTest collectorTest = new CollectorTest();
    collectorTest.example01();
  }

  void example01 () {

    List<Integer> list = Stream.of(1, 2, 3)
        .collect(Collectors.toList());
// list: [1, 2, 3]

    String s = Stream.of(1, 2, 3)
        .map(String::valueOf)
        .collect(Collectors.joining("-", "<", ">"));
    System.out.println(s);
// s: "<1-2-3>"
  }

}
