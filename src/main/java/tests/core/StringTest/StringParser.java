package tests.core.StringTest;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class StringParser {
    public static void main(String[] args) {
        StringParser stringParser = new StringParser();
        String options = "    {             host    =   ec2-52-33-224-137.us-west-2.compute.amazonaws.com,port     =      5432       ,dbname     =    test_pg_mysql              }";

     options=null;

        System.out.println(stringParser.parseOptions(options, ",","        "));


    }

    private String parseOptions(String options, String delimiter, String indention) {

        if (Objects.isNull(options) || options.trim().length() < 2) return options;
        options = options.trim();
        return Arrays.stream(options.trim().substring(1, options.length() - 1).split(","))
                .map(e -> e.split("="))
                .map(strings -> "\n" + indention + strings[0].trim() + " " + singleQuote(strings[1].trim()))
                .collect(Collectors.joining(delimiter, "(", " )"));
    }

    private static String singleQuote(String s) {
        return "\'" + s + "\'";
    }


}
