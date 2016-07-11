package t9045_batch_05;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import testrecorddl_OneConnect.Log;
import testrecorddl_OneConnect.XmlRW;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;

public class T9405 {


    public static void main(String[] args) throws Exception {
        Log.logger.info("Assessment of the duration of the recording DDL expressions Redshift");
        List<String> commands = XmlRW.readCommands("src\\data\\commandsListBatch.xml");     // XmlRW.readCommands("src\\data\\commands.xml");
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
        Log.logger.info(url);
        FileWriter writer = null;
        try {
            writer = new FileWriter("src\\log\\result-" + new SimpleDateFormat("dd-MMM-yy hh-mm-ss").format(new Date()) + ".txt", false);
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
        Log.logger.info("                                             executionTime : executionResult : description  ");

        try {
            writer.write(":   ID : executionTime     : executionResult : description  ");
            writer.append('\n');
        } catch (IOException ex) {
            Log.logger.log(Level.SEVERE, null, ex);
        }


        long startConnectTime = 0;
        long startClosingTime;
        long closingStatementTime;
        long closingConnectionTime = 0;

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

            tempTime = System.currentTimeMillis();
            con = DriverManager.getConnection(url, username, password);
            Log.logger.info(" con = DriverManager.getConnection(url, username, password); --------------------------------"+(System.currentTimeMillis() - tempTime)/1000.0);
            con.setAutoCommit(false);

            getConnectionTime = System.currentTimeMillis() - startConnectTime;
            average_getConnectionTime += getConnectionTime;

            tempTime = System.currentTimeMillis();
            st = con.createStatement();
            Log.logger.info(" con.createStatement(); --------------------------------"+(System.currentTimeMillis() - tempTime)/1000.0);
            average_getStatementTime += getStatementTime;

//--------- batch -----------------------------------------------------------------------------------
            tempTime = System.currentTimeMillis();
           for (String command : commands) {
                st.addBatch(command);
            }
            Log.logger.info("  st.addBatch(command); --------------------------------"+(System.currentTimeMillis() - tempTime)/1000.0);
            tempTime = System.currentTimeMillis();

            st.executeBatch();
            Log.logger.info("  st.executeBatch(); ---------------------------------"+(System.currentTimeMillis() - tempTime)/1000.0);

            writer.append('\n');
//--------- batch -----------------------------------------------------------------------------------
        } catch (SQLException sqlEx) {
            executionResult = "FAIL";
            countFAIL++;
            Log.logger.warning(sqlEx.getStackTrace().toString());
            sqlEx.printStackTrace();
        } finally {
            startClosingTime = System.currentTimeMillis();

            tempTime = System.currentTimeMillis();
            con.commit();
            Log.logger.info(" con.commit(); --------------------------------------"+(System.currentTimeMillis() - tempTime)/1000.0);
            tempTime = System.currentTimeMillis();
            if (st != null) st.close();
            Log.logger.info(" st.close();   --------------------------------------"+(System.currentTimeMillis() - tempTime)/1000.0);

            tempTime = System.currentTimeMillis();
            if (con != null) con.close();
            Log.logger.info(" con.close(); --------------------------------------"+(System.currentTimeMillis() - tempTime)/1000.0);
            closingConnectionTime = System.currentTimeMillis();

        }


        Log.logger.info("----------------------------------------------------------------------------------------------------------------------------------------");

        try {
            writer.write("-------------------------------------------------------------------------------------------------------------------------------------------------");
            writer.append('\n');
        } catch (IOException ex) {
            Log.logger.log(Level.SEVERE, null, ex);
        }


        Log.logger.info(String.format("        closingConnectionTime-startConnectTime:          %8.3f       ",
                (closingConnectionTime-startConnectTime) / 1000.0 ));


        writer.write(String.format("        closingConnectionTime-startConnectTime:          %8.3f       ",
                (closingConnectionTime-startConnectTime) / 1000.0 ));
        writer.append('\n');


        try {
            writer.close();
        } catch (IOException ex) {
            Log.logger.log(Level.SEVERE, null, ex);
        }
    }

}


