package testrecorddl_OneConnect_Error;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;


public class T9405_error2 {


    public static void main(String[] args) throws Exception {
        testrecorddl_OneConnect.Log.logger.info("Assessment of the duration of the recording DDL expressions Redshift");
        List<String> commands = testrecorddl_OneConnect.XmlRW.readCommands("src\\data\\commandsListError.xml");     // XmlRW.readCommands("src\\data\\commands.xml");
        //XmlRW.writeCommands("src\\data\\commandsWrite1000ListWriteL.xml", commands, "statements", "statement");
        recordDDL("rsdbb01.cqcwekr1qlta.us-west-2.redshift.amazonaws.com", 5439, "dev", "rsdbbmaster", "T8ickAvKbet3", commands);
    }

    @DataProvider(parallel = false)
    public Object[][] redshiftConnectionParams() {
        return new Object[][]{{"rsdbb01.cqcwekr1qlta.us-west-2.redshift.amazonaws.com", 5439, "dev", "rsdbbmaster", "T8ickAvKbet3", Arrays.asList(new String[]{"CREATE OR REPLACE VIEW ora_dwh.v_char_trim1 (col1) AS&#13;\n" + "SELECT&#13;\n" + "   TRIM('  HELLO   ')"})},};
    }

    @Test(dataProvider = "redshiftConnectionParams")
    public static void recordDDL(String serverName, int port, String dbName, String username, String password, List<String> commands) throws Exception {
        Class.forName("com.amazon.redshift.jdbc41.Driver");
        String url = "jdbc:redshift://" + serverName + ":" + port + "/" + dbName;
        testrecorddl_OneConnect.Log.logger.info(url);
        FileWriter writer = null;
        try {
            writer = new FileWriter("src\\log\\result-" + new SimpleDateFormat("dd-MMM-yy hh-mm-ss").format(new Date()) + ".txt", false);
        } catch (IOException ex) {
            testrecorddl_OneConnect.Log.logger.log(Level.SEVERE, null, ex);
        }

        long average_getConnectionTime = 0;
        long average_getStatementTime = 0;
        long average_executionTime = 0;
        long average_closingStatementTime = 0;
        long average_closingConnectionTime = 0;

        int count = 1;
        int countFAIL = 0;

        int size = commands.size();
        testrecorddl_OneConnect.Log.logger.info("                                                 The number of commands: " + size);
        testrecorddl_OneConnect.Log.logger.info("                                             executionTime : executionResult : description  ");

        try {
            writer.write(":   ID : executionTime     : executionResult : description  ");
            writer.append('\n');
        } catch (IOException ex) {
            testrecorddl_OneConnect.Log.logger.log(Level.SEVERE, null, ex);
        }


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

        try {
            startConnectTime = System.currentTimeMillis();
            con = DriverManager.getConnection(url, username, password);
            con.setAutoCommit(true);
            getConnectionTime = System.currentTimeMillis() - startConnectTime;
            average_getConnectionTime += getConnectionTime;

            tempTime = System.currentTimeMillis();
            st = con.createStatement();
            getStatementTime = System.currentTimeMillis() - tempTime;
            average_getStatementTime += getStatementTime;



            //for (String command : commands) {
            size =15;
            for (int i=0; i<size; i++) {
                executionResult = "SUCCESS";
                String command = commands.get(i);

                tempTime = System.currentTimeMillis();
                Savepoint savepoint = null;
                try {
                    savepoint = con.setSavepoint();
                    st.executeUpdate(command);
                } catch (SQLException sqlEx) {
                    con.rollback(savepoint);
                    executionResult = "FAIL";
                    countFAIL++;
                    testrecorddl_OneConnect_Error.Log.logger.warning(sqlEx.getStackTrace().toString());
                    sqlEx.printStackTrace();
//                    if (st != null) st.close();
//                    st = con.createStatement();
                    System.out.println("/////////////////////////////////////////////////////////");

                }

                executionTime = System.currentTimeMillis() - tempTime;
                average_executionTime += executionTime;

                String arrStr[] = command.split("\n");
                testrecorddl_OneConnect.Log.logger.info(String.format("                                 %5d :          %8.3f :        %8s : %s      ",
                        count, executionTime / 1000.0, executionResult,     arrStr[0].trim()));

                writer.write(String.format(":%5d :          %8.3f :        %8s : %s      ",
                        count++,executionTime / 1000.0, executionResult,     arrStr[0].trim()));
                writer.append('\n');


            }
//--------- batch -----------------------------------------------------------------------------------
//           for (String command : commands) {
////                for (int i=0; i<2; i++) {
////                         String command = commands.get(i);
//
//                st.addBatch(command);
//            }
//            tempTime = System.currentTimeMillis();
//            st.executeBatch();
//            executionTime = System.currentTimeMillis() - tempTime;
//
//
//            Log.logger.info(String.format("                                                %8.3f :                      ",
//                     executionTime / 1000.0));
//
//            writer.write(String.format(":    :          %8.3f :            :         ",
//                    executionTime / 1000.0));
//            writer.append('\n');
//--------- batch -----------------------------------------------------------------------------------




        } catch (SQLException sqlEx) {
            executionResult = "FAIL";
            countFAIL++;
            testrecorddl_OneConnect.Log.logger.warning(sqlEx.getStackTrace().toString());
            sqlEx.printStackTrace();
        } finally {
            startClosingTime = System.currentTimeMillis();
            con.commit();
            if (st != null) st.close();
            closingStatementTime = System.currentTimeMillis() - startClosingTime;
            average_closingStatementTime += closingStatementTime;

            if (con != null) con.close();
            closingConnectionTime = System.currentTimeMillis() - startClosingTime;
            average_closingConnectionTime += closingConnectionTime;


        }


        testrecorddl_OneConnect.Log.logger.info("----------------------------------------------------------------------------------------------------------------------------------------");

        try {
            writer.write("-------------------------------------------------------------------------------------------------------------------------------------------------");
            writer.append('\n');
        } catch (IOException ex) {
            testrecorddl_OneConnect.Log.logger.log(Level.SEVERE, null, ex);
        }


        testrecorddl_OneConnect.Log.logger.info(String.format("                         average  value:          %8.3f : %s      ",
                average_executionTime / 1000.0 / size,  "FAIL=" + countFAIL));

        writer.write(String.format(" average  value:  %8.3f : %s      ",
                average_executionTime / 1000.0 / size,  "FAIL=" + countFAIL));
        writer.append('\n');
        writer.write(String.format(" Sum      value:  %8.3f                  Min:   %8.3f    ",
                average_executionTime / 1000.0, average_executionTime / 1000.0/60.0 ));
        writer.append('\n');
        writer.write(String.format(" Amount   value:  %8d ",
                size ));
        writer.append('\n');

        try {
            writer.close();
        } catch (IOException ex) {
            testrecorddl_OneConnect.Log.logger.log(Level.SEVERE, null, ex);
        }
    }

}
