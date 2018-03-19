package tests.core.StringTest;

import java.util.Arrays;
import java.util.stream.Collectors;

public class StringParser {
    public static void main(String[] args) {
        StringParser stringParser = new StringParser();
        String options = " {             host    =   ec2-52-33-224-137.us-west-2.compute.amazonaws.com,port     =      5432       ,dbname     =    test_pg_mysql              }";


        stringParser.parseOptions(options, ",\n  ");


    }

    private void parseOptions(String options, String delimiter) {

        options = options.trim();

        String s2 = Arrays.asList(options.trim().substring(1, options.length() - 1)
                .split(","))
                .stream().map(e -> e.split("="))
                .map(strings -> strings[0].trim() + "=" + singleQuote(strings[1].trim())).collect(Collectors.joining(delimiter, "( ", " )"));


        System.out.println(s2);


    }

    private static String singleQuote(String s) {
        return "\'" + s + "\'";
    }


}
