package tests.core.privateProtectedPublic.package3;

/**
 * Created by pom on 03.10.2017.
 */
public class Main4 {
    public static void main(String[] args) throws CloneNotSupportedException {
        B b1 = new B();
        B b2 = b1.clone();
        System.out.println(b1+"     "+b2);
    }
}
