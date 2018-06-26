package tests.core.StringTest;

public class StringCommon {
    public static void main(String[] args) {
        S test = new S();
        Boolean b = test.isEmptyTest("");
        System.out.println(b);
    }

}

class S {
    public boolean isEmptyTest (String s) {
        return !s.isEmpty();
    }
}