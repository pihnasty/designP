package tests.threadtest;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.StringJoiner;

/**
 * Created by Max on 17.01.2017.
 */
public class _080_CharSet {
    public static void main(String [] arg) throws UnsupportedEncodingException, MalformedURLException {
        //_charSet();


        RIS();

    }

    private static void RIS() {
        InputStream is = null;
        try {
   //         is = new URL("http://lenta.ru").openStream();
            is = new URL("http://www.google.ru").openStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //     Reader reader = new InputStreamReader(is, "windows-1251");

        Reader reader = new InputStreamReader(is);

        char[] buff = new char[16];
        int count;

        try {
            while ((count = reader.read(buff)) != -1)
                System.out.println(new String(buff, 0, count));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) try {
                is.close();
            } catch (IOException e) {
            /*NOP*/
            }
        }
    }

    private static void _charSet() throws UnsupportedEncodingException {
        byte[] rawData = new byte[256];
        for (int k=0; k<256; k++)
            rawData[k] = (byte) (k-128);

        SortedMap<String,Charset> charset = Charset.availableCharsets();
        for (String name: charset.keySet()) {
            System.out.println(name);
        }

        for (String name: charset.keySet()) {
            System.out.println(new String(rawData,name));
            System.out.println();
        }
    }
}
