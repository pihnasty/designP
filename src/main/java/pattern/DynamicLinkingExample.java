package pattern;

class A{}

class B{}

public class DynamicLinkingExample {
    public static void main(String[] args){
        System.out.println(0);
        B b = new B();
        System.out.println(1);
        A a1 = new A();
        System.out.println(2);
        A a2 = new A();
        System.out.println(3);
    }
}