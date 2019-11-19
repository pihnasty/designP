package collection.generic.ex3;

public class Enum3 {
    public static void main(String[] args) {
        /*
        City city = new City()
                .setName("LA")
                .setSquare(100);    // won't compile, setName() returns Node
        */
    }

}

class Node {
    String name;

    Node setName(String name) {
        this.name = name;
        return this;
    }
}

class City extends Node {
    int square;

    City setSquare(int square) {
        this.square = square;
        return this;
    }
}

