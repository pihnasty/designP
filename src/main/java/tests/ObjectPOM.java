package tests;

/**
 * Created by Pihnastyi.O on 12/12/2016.
 */
public class ObjectPOM {
    static public void main (String [] args ) {
        _isEmpty();
    }

    private static void _isEmpty() {
        String s="";
        if (s.isEmpty() ) System.out.println("if (s.isEmpty() ) ");
        if ("".equals(s) ) System.out.println("if (\"\".equals(s)");
        if ("".equals(s) == s.isEmpty() ) System.out.println("if (\"\".equals(s) == s.isEmpty() )");
    }
}
