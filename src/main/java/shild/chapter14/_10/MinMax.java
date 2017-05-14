package shild.chapter14._10;

// A Min/Max interface.
interface MinMax<T extends Comparable<T>> {
    T min();
    T max();
}
