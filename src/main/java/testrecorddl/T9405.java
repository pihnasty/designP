package testrecorddl;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

public class T9405 {


    public static void main(String[] args) throws Exception {
        Log.logger.info("Assessment of the duration of the recording DDL expressions Redshift");
        List<String> commands = XmlRW.readCommands("src\\data\\commandsList.xml");     // XmlRW.readCommands("src\\data\\commands.xml");
        //XmlRW.writeCommands("src\\data\\commandsWrite1000ListWriteL.xml", commands, "statements", "statement");
        recordDDL("rsdbb01.cqcwekr1qlta.us-west-2.redshift.amazonaws.com", 5439, "dev", "rsdbbmaster", "T8ickAvKbet3", commands);
    }

    @DataProvider(parallel = false)
    public Object[][] redshiftConnectionParams() {
        return new Object[][]{{"rsdbb01.cqcwekr1qlta.us-west-2.redshift.amazonaws.com", 5439, "dev", "rsdbbmaster", "T8ickAvKbet3", Arrays.asList(new String[]{"CREATE OR REPLACE VIEW ora_dwh.v_char_trim1 (col1) AS&#13;\n" + "SELECT&#13;\n" + "   TRIM('  HELLO   ')"})},};
    }

    @Test(dataProvider = "redshiftConnectionParams")
    public static void recordDDL(String serverName, int port, String dbName, String username, String password, List<String> commands) throws Exception {
        String url = "jdbc:redshift://" + serverName + ":" + port + "/" + dbName;
        Log.logger.info(url);
        FileWriter writer = null;
        try {
            writer = new FileWriter("src\\log\\result.txt", false);
        } catch (IOException ex) {
            Log.logger.log(Level.SEVERE, null, ex);
        }

        long average_getConnectionTime = 0;
        long average_getStatementTime = 0;
        long average_executionTime = 0;
        long average_closingStatementTime = 0;
        long average_closingConnectionTime = 0;

        int count = 1;
        int countFAIL = 0;

        int size = commands.size();
        Log.logger.info("                                                 The number of commands: " + size);
        Log.logger.info("                                                 totalConnectionClosingTime : connectionClosingTime : statementClosingTime : executeTime");

        try {
            writer.write(":   ID : getConnectionTime : getStatementTime : executionTime : closingStatementTime : closingConnectionTime : executionResult : description  ");
            writer.append('\n');
        } catch (IOException ex) {
            Log.logger.log(Level.SEVERE, null, ex);
        }


        for (String command : commands) {
            long startConnectTime;
            long startClosingTime;
            long closingStatementTime;
            long closingConnectionTime;

            long getConnectionTime = 0;


            long getStatementTime = 0;
            long executionTime = 0;
            long tempTime = 0;

            String executionResult = "SUCCESS";
            String description = "";

            Connection con = null;
            Statement st = null;
            Class.forName("com.amazon.redshift.jdbc41.Driver");
            try {
                startConnectTime = System.currentTimeMillis();
                con = DriverManager.getConnection(url, username, password);
                getConnectionTime = System.currentTimeMillis() - startConnectTime;
                average_getConnectionTime += getConnectionTime;

                tempTime = System.currentTimeMillis();
                st = con.createStatement();
                getStatementTime = System.currentTimeMillis() - tempTime;
                average_getStatementTime += getStatementTime;

                tempTime = System.currentTimeMillis();
                st.executeUpdate(command);
                executionTime = System.currentTimeMillis() - tempTime;
                average_executionTime += executionTime;

            } catch (SQLException sqlEx) {
                executionResult = "FAIL";
                countFAIL++;
                Log.logger.warning(sqlEx.getStackTrace().toString());
                sqlEx.printStackTrace();
            } finally {
                startClosingTime = System.currentTimeMillis();

                if (st != null) st.close();
                closingStatementTime = System.currentTimeMillis() - startClosingTime;
                average_closingStatementTime += closingStatementTime;

                if (con != null) con.close();
                closingConnectionTime = System.currentTimeMillis() - startClosingTime;
                average_closingConnectionTime += closingConnectionTime;

                String arrStr[] = command.split("\n");
                Log.logger.info(String.format("                                 %5d :          %8.3f :         %8.3f :      %8.3f :             %8.3f :              %8.3f :        %8s : %s      ",
                        count, getConnectionTime / 1000.0, getStatementTime / 1000.0, executionTime / 1000.0, closingStatementTime / 1000.0, closingConnectionTime / 1000.0, executionResult,
                        arrStr[0].trim()));
                try {
                    writer.write(String.format(":%5d :          %8.3f :         %8.3f :      %8.3f :             %8.3f :              %8.3f :        %8s : %s      ",
                            count++, getConnectionTime / 1000.0, getStatementTime / 1000.0, executionTime / 1000.0, closingStatementTime / 1000.0, closingConnectionTime / 1000.0, executionResult, arrStr[0].trim()));
                    writer.append('\n');
                } catch (IOException ex) {
                    Log.logger.log(Level.SEVERE, null, ex);
                }

            }

        }
        Log.logger.info("----------------------------------------------------------------------------------------------------------------------------------------");

        try {
            writer.write("-------------------------------------------------------------------------------------------------------------------------------------------------");
            writer.append('\n');
        } catch (IOException ex) {
            Log.logger.log(Level.SEVERE, null, ex);
        }


        Log.logger.info(String.format("                                         average  value:  %8.3f :         %8.3f :      %8.3f :             %8.3f :              %8.3f :        %8s :      ",
                average_getConnectionTime / 1000.0 / size, average_getStatementTime / 1000.0 / size, average_executionTime / 1000.0 / size, average_closingStatementTime / 1000.0 / size,
                average_closingConnectionTime / 1000.0 / size, "FAIL=" + countFAIL));

        writer.write(String.format(" average  value:  %8.3f :         %8.3f :      %8.3f :             %8.3f :              %8.3f :        %8s :      ",
                average_getConnectionTime / 1000.0 / size, average_getStatementTime / 1000.0 / (size - countFAIL), average_executionTime / 1000.0 / (size - countFAIL), average_closingStatementTime / 1000.0 / (size - countFAIL),
                average_closingConnectionTime / 1000.0 / size, "FAIL=" + countFAIL));
        writer.append('\n');


        try {
            writer.close();
        } catch (IOException ex) {
            Log.logger.log(Level.SEVERE, null, ex);
        }
    }

}


