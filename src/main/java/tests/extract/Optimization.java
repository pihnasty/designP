package tests.extract;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.junit.Test;

public class Optimization {
    private String fileName = "src\\main\\java\\tests\\extract\\data.txt";


    public static void main(String[] args) throws IOException {

        int numberPartitions = 10;

        double result;

        for (int i=1; i<numberPartitions; i++) {
            result = getResult(numberPartitions,i);
            System.out.println("end for partition "+i+"  Approximate valueprimary key ="+result);
        }
    }

    private static double getResult(int numberAllPartitions, int numberPartition) throws IOException {
        Optimization optimization = new Optimization();
        //      optimization.writeInFile();
//        Select select = new Select(optimization.fileName);
//        select.readFromFile();

        Select select = new SelectDataBase();

        double [] b = {
                select.selectSumX2Y(),
                select.selectSumXY(),
                select.selectSumY()
        };

        double counter =select.selectCount();

        double x4 =select.x4(counter);
        double x3 =select.x3(counter);
        double x2 =select.x2(counter);
        double x1 =select.x(counter);

        double[][] a = {
                {x4, x3, x2},
                {x3, x2, x1},
                {x2, x1, counter}
        };





        double [][] aDouble = new double[a.length][a.length];
        double []   bDouble = new double[a.length];

        for (int i1 = 0; i1 <a.length; i1++) {
            for (int i2 = 0; i2 < a.length; i2++) {
                aDouble[i1][i2] = a[i1][i2];
            }
            bDouble[i1] = b[i1];
        }


        double alfa [] = optimization.methodGauss(aDouble,bDouble);

//             optimization.showB(b);
//              optimization.showA(a);
//              optimization.showAlfa(alfa);
        System.out.print("     rnumber ="+counter/numberAllPartitions*numberPartition+"   ");
        return optimization.myFunction(alfa,counter/numberAllPartitions*numberPartition);
    }

    private void showB(long [] b) {
        System.out.println("Coefficients b[i]------------------------------------------------");
        for (int i =0; i<b.length; i++) System.out.println(String.format("%20d ",b[i]));
    }

    private void showA(long [][] a) {
        System.out.println("Coefficients a[i1,i2]------------------------------------------------");
        for (int i1 =0; i1<a.length; i1++) {
            for (int i2 =0; i2<a.length; i2++) {
                System.out.print(   String.format("%20d ",     a[i1][i2]));
            }
            System.out.println();
        }
    }

    private void showAlfa(double [] alfa) {
        System.out.println("Coefficients alfa[i]------------------------------------------------");
        for (int i =0; i<alfa.length; i++) System.out.println(String.format("%20f ",alfa[i]));
    }

    private double myFunction (double [] alfa, double i){
        double sum = 0;

        for (int i1=0; i1< alfa.length; i1++) {
            sum+=Math.pow(i,i1)*alfa[alfa.length-1-i1];
        }


        return sum ;
    }



    public double[] methodGauss(double[][] aInput, double[] bInput) {

        int dimension = aInput.length;
        if (dimension != bInput.length) return null;

        double[][] a = new double[dimension][dimension];
        double[] b = new double[dimension];


        for (int i1 = 0; i1 < dimension; i1++) {
            for (int i2 = 0; i2 < dimension; i2++) {
                a[i1][i2] = aInput[i1][i2];
            }
            b[i1] = bInput[i1];
        }


        for (int p = 0; p < dimension; p++) {
            int max = p;
            for (int i = p + 1; i < dimension; i++) {
                if (Math.abs(a[i][p]) > Math.abs(a[max][p])) {
                    max = i;
                }
            }

            double[] tempA = a[p];
            a[p] = a[max];
            a[max] = tempA;
            double tempB = b[p];
            b[p] = b[max];
            b[max] = tempB;

            if (Math.abs(a[p][p]) <= 1e-10) {
                System.out.println("NO");
                return null;
            }

            for (int i = p + 1; i < dimension; i++) {
                double alpha = a[i][p] / a[p][p];
                b[i] -= alpha * b[p];
                for (int j = p; j < dimension; j++) {
                    a[i][j] -= alpha * a[p][j];
                }
            }
        }

        double[] x = new double[dimension];
        for (int i = dimension - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i + 1; j < dimension; j++) {
                sum += a[i][j] * x[j];
            }
            x[i] = (b[i] - sum) / a[i][i];
        }
        return x;
    }


    private void writeInFile(){

        long primaryKeyStart = -10_000;
        long primaryKey = primaryKeyStart;
        long counter = 1;

        Random random = new Random();

        try ( FileWriter writer =new FileWriter(new File(fileName)) ){

            while (counter<1000) {
                String sf = String.format("%20d  :%20d \n", primaryKey, counter);
                writer.write(sf);
                counter++;
                primaryKey+=random.nextInt(100)+1;
                        //counter*counter;       //+= 1; //random.nextInt(100)+1;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMethodGaussa(){
        double[][] a = {
                {1, -2, 0},
                {-7, 2, -9},
                {-9, -1, 3}
        };
        double[] b = {
                5.0,
                1.0,
                2.0
        };

        double eps = 0.0000001;

        double [] x = methodGauss( a, b);

        assert(Math.abs(b[0]-(x[0]*a[0][0]+x[1]*a[0][1]+x[2]*a[0][2]))<eps) ;
        assert(Math.abs(b[1]-(x[0]*a[1][0]+x[1]*a[1][1]+x[2]*a[1][2]))<eps) ;
        assert(Math.abs(b[2]-(x[0]*a[2][0]+x[1]*a[2][1]+x[2]*a[2][2]))<eps) ;

        System.out.println( "b[0]="+(x[0]*a[0][0]+x[1]*a[0][1]+x[2]*a[0][2]));
        System.out.println( "b[1]="+(x[0]*a[1][0]+x[1]*a[1][1]+x[2]*a[1][2]));
        System.out.println( "b[2]="+(x[0]*a[2][0]+x[1]*a[2][1]+x[2]*a[2][2]));
    }

}


class Select {
    private String fileName;
    List<List<Long>> lines = new ArrayList<>();

    public Select () {
    }

    public Select (String fileName) {
        this.fileName = fileName;
    }

    public void readFromFile() throws IOException {


      BufferedReader reader = new BufferedReader(new FileReader(fileName));

        String line;

        while ((line = reader.readLine()) != null) {
            String strings [] = line.split(":");
            long primaryKey = Long.parseLong(strings[0].trim());
            long counter =  Long.parseLong(strings[1].trim());

            List<Long> pair = new ArrayList<>();
            pair.add(primaryKey);
            pair.add(counter);
            lines.add(pair);
     //       System.out.println(primaryKey+"   "+ counter);
        }



    }

    public double selectCount () {
        return lines.size();
    }

    public double selectSumX2Y () {
        long sum=0;
        for (List<Long> line : lines) {
            sum+=line.get(0)*line.get(1)*line.get(1);
        }
        return sum;
    }

    public double selectSumXY () {
        long sum=0;
        for (List<Long> line : lines) {
            sum+=line.get(0)*line.get(1);
        }
        return sum;
    }

    public double selectSumY () {
        long sum=0;
        for (List<Long> line : lines) {
            sum+=line.get(0);
        }
        return sum;
    }

    public double x4 (double counter) {
        double sum=0;
        for (double i=1; i<=counter; i++) {
            sum += i*i*i*i;
        }
        return sum;
    }

    public double x3 (double counter) {
        double sum=0;
        for (double i=1; i<=counter; i++) {
            sum += i*i*i;
        }
        return sum;
    }

    public double x2 (double counter) {
        double sum=0;
        for (double i=1; i<=counter; i++) {
            sum += i*i;
        }
        return sum;
    }

    public double x (double counter) {
        double sum=0;
        for (double i=1; i<=counter; i++) {
            sum +=i;
        }
        return sum;
    }
}

class SelectDataBase extends Select {

    private double selectSumY;
    private double selectSumXY;
    private double selectSumX2Y;
    private double counter;
    private double x;
    private double x2;
    private double x3;
    private double x4;


    public SelectDataBase () {
        String JDBC_DRIVER = "oracle.jdbc.OracleDriver";
        String DB_URL = "jdbc:oracle:thin:@192.168.3.153:1521:ORCL";
        String path = "jar:file:C:///JDBCDrivers/ojdbc7.jar!/";
        String USER = "min_privs";
        String PASS = "min_privs";

        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            URLClassLoader cl = new URLClassLoader(new URL[]{new URL(path)});
            cl.loadClass(JDBC_DRIVER).newInstance();
            Driver driver = DriverManager.getDriver(DB_URL);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            int i = 3;
            String sql = getSql(i);
            stmt = conn.prepareStatement(sql);
            readColumn(stmt);

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {

            e.printStackTrace();
        } finally {

            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }


    }

    private static String getSql(int i) {
        String sql = "";
        switch (i)        {
            case 1:
                sql = "select\n" +
                    "     sum(row_size) as selectSumY, sum(row_size*rnumber) as selectSumXY, sum(row_size*rnumber*rnumber) as selectSumX2Y,  max(rnumber) as counter,  sum(rnumber) as x,  sum(rnumber*rnumber) as x2,  sum(rnumber*rnumber*rnumber) as x3, sum(rnumber*rnumber*rnumber*rnumber) as x4 \n" +
                    "from \n" +
                    "(\n" +
                    "select  \n" +
                    "   ID row_size ,   rownum rnumber \n" +
                    "from\n" +
                    "(\n" +
               //     "select (ID-10000) as ID  from \"ORA_DATA4EXTRACTOR\".\"TABLE_PART10000\"\n" +
                    "select ID as ID  from \"ORA_DATA4EXTRACTOR\".\"TABLE_PART369\"\n" +
                    "order by ID\n" +
                    ")\n" +
                    "\n" +
                    ")";
                break;
            case 2:
                sql = "select\n" +
                    "    sum(row_size) as selectSumY, sum(row_size*rnumber) as selectSumXY, sum(row_size*rnumber*rnumber) as selectSumX2Y,  max(rnumber) as counter,  sum(rnumber) as x,  sum(rnumber*rnumber) as x2,  sum(rnumber*rnumber*rnumber) as x3, sum(rnumber*rnumber*rnumber*rnumber) as x4\n" +
                    "from \n" +
                    "(\n" +
                    "select  \n" +
                    "   ID row_size ,   rownum*0.01 rnumber \n" +
                    "from\n" +
                    "(\n" +
                    "select ID as ID from \"ORA_DATA4EXTRACTOR\".\"TABLE_PART10000\"\n" +
                    "order by ID\n" +
                    ")\n" +
                    "\n" +
                    ")\n";
                break;
            case 3:
                sql = "select\n" +
                    "    sum(row_size) as selectSumY, sum(row_size*rnumber) as selectSumXY, sum(row_size*rnumber*rnumber) as selectSumX2Y, max(rnumber) as counter,  sum(rnumber) as x,  sum(rnumber*rnumber) as x2,  sum(rnumber*rnumber*rnumber) as x3, sum(rnumber*rnumber*rnumber*rnumber) as x4\n" +
                    "from \n" +
                    "(\n" +
                    "select  \n" +
                    "   ID row_size ,   (rownum*0.0001)as  rnumber \n" +
                    "from\n" +
                    "(\n" +
                    "select LO_CUSTKEY as ID from \"ORA_DATA4EXTRACTOR\".\"LINEORDER_1M\"\n" +
                    "order by LO_CUSTKEY\n" +
                    ")\n" +
                    "\n" +
                    ")";
                break;
            case 4:
                sql = "select\n" +
                    "    sum(row_size) as selectSumY, sum(row_size*rnumber) as selectSumXY, sum(row_size*rnumber*rnumber) as selectSumX2Y, max(rnumber) as counter,  sum(rnumber) as x,  sum(rnumber*rnumber) as x2,  sum(rnumber*rnumber*rnumber) as x3, sum(rnumber*rnumber*rnumber*rnumber) as x4\n" +
                    "from \n" +
                    "(\n" +
                    "select  \n" +
                    "   ID row_size ,   (rownum*0.000001)as  rnumber \n" +
                    "from\n" +
                    "(\n" +
                    "select LO_CUSTKEY as ID from \"ORA_DATA4EXTRACTOR\".\"LINEORDER_75M\"\n" +
                    "order by LO_CUSTKEY\n" +
                    ")\n" +
                    "\n";
                break;
            default:
                sql = "";
        }
        return sql;
    }

    private void readColumn(PreparedStatement stmt) throws SQLException {
        ResultSet rs = stmt.executeQuery();
        while(rs.next())  {
            selectSumY = rs.getDouble("selectSumY");
            selectSumXY = rs.getDouble("selectSumXY");
            selectSumX2Y = rs.getDouble("selectSumX2Y");
            counter = rs.getDouble("counter");
            x = rs.getDouble("x");
            x2 = rs.getDouble("x2");
            x3 = rs.getDouble("x3");
            x4 = rs.getDouble("x4");
        }
       rs.close();
    }

    @Override
    public double selectCount () {
        return counter;
    }

    @Override
    public double selectSumX2Y () {
        return selectSumX2Y;
    }

    @Override
    public double selectSumXY () {
        return selectSumXY;
    }

    @Override
    public double selectSumY () {
        return selectSumY;
    }

    @Override
    public double x4 (double counter) {
        return x4;
    }

    @Override
    public  double x3 (double  counter) {
        return x3;
    }

    @Override
    public  double x2 (double  counter) {
        return x2;
    }

    @Override
    public double x (double  counter) {
        return x;
    }
}