package tests.core.overrides;

public class test {
    public static void main(String[] args) {
        B b = new B();
        C c = new C();
        System.out.println(b.create(""));
        System.out.println(c.create(""));


        A a = new A();

        a = b;
        System.out.println(a.create(""));
        a=c;
        System.out.println(a.create(""));

    }
}


class A  {
    public String create (String s) {
        return "Hello A";
    }
}

class B extends A  {
    public String create (String s) {
        return "Hello B";
    }
}

class C extends A  {
    @Override
    public String create (String s) {
        return "Hello C";
    }
}