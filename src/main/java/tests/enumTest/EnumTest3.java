package tests.enumTest;

import java.util.HashMap;

public class EnumTest3 {
    public enum AuthenticationMode {
        WINDOWS("Windows Authentication"),
        SQL_SERVER("SQL Server Authentication");

        public String label;

        public static HashMap map = new HashMap() {
            {
                put(WINDOWS.label.toLowerCase(), WINDOWS);
                put(SQL_SERVER.label.toLowerCase(), SQL_SERVER);
            }
        };

        AuthenticationMode(String label) {
            this.label = label;
        }

        public static AuthenticationMode findByLabel(String label) {
                    return (AuthenticationMode) map.get(label.toLowerCase());
        }

        public String getLabel() {
            return label;
        }

        @Override
        public String toString() {
            return label;
        }
    }

    public static void main(String[] args) {
        AuthenticationMode a;
        a = AuthenticationMode.SQL_SERVER;

        AuthenticationMode c;
        c = AuthenticationMode.WINDOWS;

     //   AuthenticationMode b = AuthenticationMode.valueOf("WWWW");

        if (AuthenticationMode.WINDOWS == AuthenticationMode.findByLabel("SQL SERVER AUTHENTICATION")  )   System.out.println("Hello");

        System.out.println( AuthenticationMode.findByLabel("Windows Authentication")   );

       // System.out.println(c.toString());

        System.out.println("HeLlo+".toLowerCase());

    }
}

