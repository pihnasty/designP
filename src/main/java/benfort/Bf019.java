package benfort;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static java.lang.System.*;

class Bf019 {
    public static void main(String args[]) {
        Pattern pat = Pattern.compile("[Cc][Aa][Rr]");
        Matcher mat = pat.matcher("  scar,  carry  и  incarc");

        while(mat.find()) {
            out.println("test found at index " +  mat.start()+ "    "+mat.group());
        }
    }
}