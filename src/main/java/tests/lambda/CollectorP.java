package tests.lambda;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectorP {

  public static void main(String[] args) {
    CollectorP comparatorP = new CollectorP();
    System.out.println(comparatorP.example01());
    System.out.println(comparatorP.example02());

    MedianCollector medianCollector =  new MedianCollector();

    Supplier<TreeSet<Integer>>  supplierTreeSet = medianCollector.supplier();

    supplierTreeSet.getClass();
    supplierTreeSet.get();


    BinaryOperator<TreeSet<Integer>> bo = (l, r) -> { l.addAll(r); return l; };


    TreeSet<Integer> treeSet = new TreeSet<>();
    treeSet.add(1);
    treeSet.add(2);
    treeSet.add(3);
    treeSet.add(4);
    treeSet.add(5);
    treeSet.add(1);


    Function<TreeSet<Integer>, Integer> f =  s -> {
      long size = s.size();
      if (size%2==0) {
        return new Double(s
            .stream()
            .skip(size % 2+2)
            .limit(2)
            .mapToInt(i->i)
            .average()
            .getAsDouble())
            .intValue();
      }
      return s
          .stream()
          .skip(size % 2+2)
          .findFirst()
          .get();
    };

    int iTreeSet = f.apply(treeSet);

    System.out.println(iTreeSet);

    Integer list = treeSet
        .stream()
        .collect(medianCollector);

    System.out.println("list="+ list);


    List<Integer> list2 = treeSet.stream().collect(Collectors.toList());


  }





  List example01(){
      return Stream.of(1, 2, 3, 1, 9, 2, 5, 3, 4, 8, 2)
          .collect(Collectors.collectingAndThen(
              Collectors.toCollection(LinkedHashSet::new),
              ArrayList::new));
  }

  Set example02(){
    Set<Integer> elements = new HashSet<>();
    Stream.of(1, 2, 3, 1, 9, 2, 5, 3, 4, 8, 2)
        .collect(Collectors.partitioningBy(elements::add))
        .forEach((isUnique, list) -> System.out.format("%s: %s%n", isUnique ? "unique" : "repetitive", list));
    return elements;
  }

}

class MedianCollector implements Collector <Integer, TreeSet<Integer>, Integer> {

  /**
   * Supplier возвращает лямбда-выражение, создающее контейнер для хранения промежуточных выражений:
   * @return
   */
  @Override
  public Supplier<TreeSet<Integer>> supplier() {
    //return TreeSet<Integer> ::new;
    return   () -> new TreeSet<>();
  }

  /**
   * Accumulator добавляет очередное значение в контейнер промежуточных значений.
   * Если быть точным, то accumulator возвращает лямбда-выражение,
   * которое обрабатывает очередное значение и сохраняет его.
   * @return
   */
  @Override
  public BiConsumer<TreeSet<Integer>, Integer> accumulator() {
    //return TreeSet::add;
    return (e1,e2) -> e1.add(e2);
  }

  /**
   * Combiner возвращает лямбда-выражение, объединяющее
   * два контейнера промежуточных значений в один. Дело в том,
   * что Stream API может создать несколько таки контейнеров,
   * для параллельной обработки и в конце слить их в один общий контейнер.
   * @return
   */
  @Override
  public BinaryOperator<TreeSet<Integer>> combiner() {
    return (l, r) -> { l.addAll(r); return l; };    // [TreeSet r] добавляет в [TreeSet L]
  }

  /**
   * Finisher возвращает лямбда-выражение, которое производит финальное преобразование:
   * обрабатывает содержимого контейнера промежуточных результатов и приводит его к заданному выходному типу.
   * @return
   */
  @Override
  public Function<TreeSet<Integer>, Integer> finisher() {
    return s -> {
      long size = s.size();
      if (size%2==0) {
        return new Double(s
            .stream()
            .skip(size % 2+2)
            .limit(2)
            .mapToInt(i->i)
            .average()
            .getAsDouble())
            .intValue();
      }
      return s
          .stream()
          .skip(size % 2+1)
          .findFirst()
          .get();
    };
  }

  @Override
  public Set<Characteristics> characteristics() {
    return EnumSet.of(Characteristics.CONCURRENT);
  }
}


class B {
  void m() {
    Function<Integer, String> f = a ->new String();
    Function<String, String> f2 = String::new;
  }
}

class MedianCollector2 implements Collector<Integer, TreeSet<Integer>, Integer> {
  @Override
  public Supplier<TreeSet<Integer>> supplier() {
    return TreeSet<Integer>::new;
  }

  @Override
  public BiConsumer<TreeSet<Integer>, Integer> accumulator() {
    return TreeSet::add;
  }

  @Override
  public BinaryOperator<TreeSet<Integer>> combiner() {
    return (l, r) -> { l.addAll(r); return l; };
  }

  @Override
  public Function<TreeSet<Integer>, Integer> finisher() {
    return s -> {
      long size = s.size();
      if (size%2==0) {
        return new Double(s
            .stream()
            .skip(size % 2+2)
            .limit(2)
            .mapToInt(i->i)
            .average()
            .getAsDouble())
            .intValue();
      }
      return s
          .stream()
          .skip(size % 2+2)
          .findFirst()
          .get();
    };
  }

  @Override
  public Set<Characteristics> characteristics() {
    return EnumSet.of(Characteristics.CONCURRENT);
  }
}



