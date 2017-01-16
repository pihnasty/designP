package tests.threadtest;

import java.io.*;
import java.util.Arrays;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Created by Max on 16.01.2017.
 */
public class _078_adapter_DataInput  {
    public static void main (String [] args) throws IOException{
        writeToFile();
        readToFile();
    }

    private static void readToFile() throws IOException {
        GZIPInputStream inputStream = new GZIPInputStream(
          new BufferedInputStream(
                  new FileInputStream(
                          new File("D:\\POM\\designP\\src\\main\\java\\tests\\threadtest\\_078_M\\001.bin")
                  )
          )
        );
        DataInput dataInput = new DataInputStream(inputStream);

        byte age = dataInput.readByte();
        String name = dataInput.readUTF();
        int [] salaryArr = new int[dataInput.readInt()];
        for (int k=0; k<salaryArr.length; k++) salaryArr[k] = dataInput.readInt();
        inputStream.close();

        System.out.println(age);
        System.out.println(name);
        System.out.println(Arrays.toString(salaryArr));


    }
    private static void writeToFile() throws IOException {
        byte age =45;
        String name = "Mike";
        int[] salarArray  = {200,300,250,150};

        GZIPOutputStream outputStream = new GZIPOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(
                                new File("D:\\POM\\designP\\src\\main\\java\\tests\\threadtest\\_078_M\\001.bin")
                        )
                )
        );
        DataOutput dataOutput = new DataOutputStream(outputStream);
        dataOutput.writeByte(age);
        dataOutput.writeUTF(name);
        dataOutput.writeInt(salarArray.length);
        for (int salaryItem: salarArray) dataOutput.writeInt(salaryItem);

        outputStream.flush();
        outputStream.close();




    }
}
