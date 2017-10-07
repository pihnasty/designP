package tests.core.privateProtectedPublic.package3;

/**
 * Created by pom on 03.10.2017.
 */
public class B implements Cloneable {
    public B clone() throws CloneNotSupportedException {
        return (B)super.clone();
    }
}
