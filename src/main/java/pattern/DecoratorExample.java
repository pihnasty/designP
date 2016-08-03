package pattern;

interface InterfaceComponent {  void doOperation();   }

class MainComponent implements InterfaceComponent {

    @Override
    public void doOperation() {
        System.out.println("World!");
    }
}

abstract class Decorator implements InterfaceComponent {
    protected InterfaceComponent component;

    public Decorator (InterfaceComponent c) {
        component = c;
    }

    @Override
    public void doOperation() {
        component.doOperation();
    }

    public void newOperation() {
        System.out.println("Do Nothing");
    }
}

class Decorator1 extends Decorator{

    public Decorator1(InterfaceComponent c) {
        super(c);
    }

    @Override
    public void doOperation() {
        System.out.print(" 1 ");
        super.doOperation();
    }

    @Override
    public void newOperation() {
        System.out.println("New1New");
    }
}

class Decorator2 extends Decorator {

    public Decorator2(InterfaceComponent c) {
        super(c);
    }

    @Override
    public void doOperation() {
        System.out.print(" 2 ");
        super.doOperation();
    }

    @Override
    public void newOperation() {
        System.out.println("New2New");
    }
}

class Decorator3 extends Decorator {

    public Decorator3(InterfaceComponent c) {
        super(c);
    }

    @Override
    public void doOperation() {
        System.out.print(" 3 ");
        super.doOperation();
    }

    @Override
    public void newOperation() {
        System.out.println("New3New");
    }
}

public class DecoratorExample {

    public static void main (String... s) {
        Decorator c = new Decorator3(new Decorator2(new Decorator3(new MainComponent())));
        c.doOperation(); // Результат выполнения программы "Hello, World!"
        c.newOperation(); // New hello operation
    }
}