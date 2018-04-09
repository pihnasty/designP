package tests.extract;

import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        Select select = new Select(optimization.fileName);
        select.readFromFile();


        long [] b = {
                select.selectSumX2Y(),
                select.selectSumXY(),
                select.selectSumY()
        };

        long counter =select.selectCount();

        long x4 =optimization.x4(counter);
        long x3 =optimization.x3(counter);
        long x2 =optimization.x2(counter);
        long x1 =optimization.x(counter);

        long[][] a = {
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

        //      optimization.showB(b);
        //      optimization.showA(a);
        //      optimization.showAlfa(alfa);
        System.out.print("     line="+counter/numberAllPartitions*numberPartition+"   ");
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

    private double myFunction (double [] alfa, long i){
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

    private Long x4 (long counter) {
        long sum=0;
        for (int i=1; i<=counter; i++) {
            sum += i*i*i*i;
        }
        return sum;
    }

    private Long x3 (long counter) {
        long sum=0;
        for (int i=1; i<=counter; i++) {
            sum += i*i*i;
        }
        return sum;
    }

    private Long x2 (long counter) {
        long sum=0;
        for (int i=1; i<=counter; i++) {
            sum += i*i;
        }
        return sum;
    }

    private Long x (long counter) {
        long sum=0;
        for (int i=1; i<=counter; i++) {
            sum +=i;
        }
        return sum;
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

    public long selectCount () {
        return lines.size();
    }

    public long selectSumX2Y () {
        long sum=0;
        for (List<Long> line : lines) {
            sum+=line.get(0)*line.get(1)*line.get(1);
        }
        return sum;
    }

    public long selectSumXY () {
        long sum=0;
        for (List<Long> line : lines) {
            sum+=line.get(0)*line.get(1);
        }
        return sum;
    }

    public long selectSumY () {
        long sum=0;
        for (List<Long> line : lines) {
            sum+=line.get(0);
        }
        return sum;
    }

}

