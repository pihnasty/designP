package tests.FuntionalInterface;

import static java.lang.Character.isDigit;
import static java.util.stream.Collectors.toList;
import java.util.List;
import java.util.stream.Stream;

public class Comparators {
  public void show() {
    List<String> beginningWithNumbers =
  Stream.of("a",  "labc",  "abcl")
        .filter(value  ->  isDigit(value.charAt(0)))
        .collect(toList());




  }
}
