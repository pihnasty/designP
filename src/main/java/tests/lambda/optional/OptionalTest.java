package tests.lambda.optional;

import java.util.Optional;

public class OptionalTest {
    public static void main(String[] args) {
        System.out.println("="+switchTest());
    }

    public static String switchTest() {

            String indexType = "";
            String s = "FULLTEXT";
            s="";
            s=null;
        s = "FULLTEXT";


            switch (Optional.ofNullable(s).orElse("")) {
                case "FULLTEXT":
                    indexType = "FULLTEXT ";
                    break;
                case "SPATIAL":
                    indexType = "SPATIAL ";
                    break;
            }
            return indexType;
        }

}
