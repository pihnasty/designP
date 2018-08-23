package jdbcTest.oracle;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.SortedMap;

class Encodings
{
    public static void main(String[] args) throws IOException
    {
        String s = "Good news everyone!";
        byte[] buffer = s.getBytes("Windows-1251");//создать массив байт в любой известной Java кодировке

        SortedMap<String, Charset> charsets = Charset.availableCharsets();//список доступных кодировок
        Charset currentCharset = Charset.defaultCharset();//узнать текущую кодировку


        FileInputStream  inputStream = new FileInputStream("C:\\Users\\pihnastyi.o\\Desktop\\Tects\\22561 Bug  DBBTOOL-910. BANINST1. Incorrect package SQL text loading\\7.txt");
        FileOutputStream outputStream = new FileOutputStream("C:\\Users\\pihnastyi.o\\Desktop\\Tects\\22561 Bug  DBBTOOL-910. BANINST1. Incorrect package SQL text loading\\7a.txt");

        byte[] fileBuffer = new byte[10000];
        inputStream.read(fileBuffer);
        String s1 = new String(fileBuffer, "Windows-1251");//преобразовать набор байт, прочитанных из файла в строку

        //преобразовать набор байт из одной кодировки в другую
//        Charset koi8 = Charset.forName("KOI8-R");
        Charset UTF8 = Charset.forName("UTF-8");
        Charset UTF16 = Charset.forName("UTF-16");

        Charset windows1251 = Charset.forName("Windows-1251");

        byte[] buffer3 = new byte[10000];
        inputStream.read(buffer3);
        String s3 = new String(buffer3, UTF16);
        buffer3 = s3.getBytes(UTF16);
        outputStream.write(buffer3);
    }
}
