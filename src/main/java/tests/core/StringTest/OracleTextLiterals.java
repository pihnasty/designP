package tests.core.StringTest;

public class OracleTextLiterals {
    public static void main(String[] args) {

        OracleTextLiterals oracleTextLiterals = new OracleTextLiterals();

        char c = 39;

        String literal = "Employee"+c+ "name";

        System.out.println(        oracleTextLiterals.escapeSingleQuote(literal)
        );


    }

    private String escapeSingleQuote(String literal) {
        return literal.contains("'")?"q'["+literal+"]'":literal;
    }

}
