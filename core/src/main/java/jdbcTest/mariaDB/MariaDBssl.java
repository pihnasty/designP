package jdbcTest.mariaDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.Test;

public class MariaDBssl {

    private void checkConnection() {
        try (Connection con = DriverManager.getConnection("jdbc:mariadb://mariadb-10-2.cjj06khxetlc.us-west-2.rds.amazonaws.com?" +
            "user=min_privs&password=min_privs"
            + "&useSSL=true")) {
            try (Statement stmt = con.createStatement()) {
                ResultSet rs = stmt.executeQuery(SqlText.getSqlText(1));

                while (rs.next()) {
                    System.out.println("text: " + rs.getString(2));
                }
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkConnectionTest() {
        checkConnection();
    }
}

class SqlText {

    public static String getSqlText(int i) {
        switch (i) {
            case 1:
                return "                        SELECT `TABLE_SCHEMA`\n" +
                    "                        , `TABLE_NAME`\n" +
                    "                        , `ENGINE`\n" +
                    "                        , `TABLE_COLLATION`\n" +
                    "                        , `TABLE_COMMENT`\n" +
                    "                        , `ROW_FORMAT`\n" +
                    "                        , `CREATE_OPTIONS`\n" +
                    "                        FROM information_schema.TABLES\n" +
                    "                        WHERE `TABLE_TYPE` = 'BASE TABLE'\n" +
                    "                        ORDER BY `TABLE_SCHEMA`\n" +
                    "                        , `TABLE_NAME`;";
            default:
                return "";
        }
    }
}

