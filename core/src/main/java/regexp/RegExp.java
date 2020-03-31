package regexp;

public class RegExp {
    public static void main(String[] args) {
        String s = "Number (34567)";
        s = s.replace("/([^\\)]+)\\((.*)\\)/","");
        System.out.println(s);
    }
}
