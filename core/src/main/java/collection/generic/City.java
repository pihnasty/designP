package collection.generic;

abstract class Node<SELF >{
    String name;

    SELF setName(String name) {
        this.name = name;
        return self();
    }

    protected abstract SELF self();
}

public class City extends Node<City> {
    int square;

    City setSquare(int square) {
        this.square = square;
        return self();
    }

    @Override
    protected City self() {
        return this;
    }

    public static void main(String[] args) {
        City city = new City()
                .setName("LA")
                .setSquare(100);                 // ok!
    }
}

class City2 extends City {

}