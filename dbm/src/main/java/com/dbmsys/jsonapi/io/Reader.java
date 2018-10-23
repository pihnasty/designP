package com.dbmsys.jsonapi.io;

import com.dbmsys.jsonapi.gson.deserialize.BodyDeserializer;
import com.dbmsys.jsonapi.gson.deserialize.DmsSysArrayElementDeserializer;
import com.dbmsys.jsonapi.template.Body;
import com.dbmsys.jsonapi.template.DmsSysElement;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.zip.GZIPInputStream;

//http://www.javenue.info/post/gson-json-api

public class Reader {

    public void readFromGzFile(String path, String fileName) {

        String fullName = path + fileName;
        try (FileInputStream fileInputStream = new FileInputStream(fullName);
             GZIPInputStream gzipInputStream = new GZIPInputStream(fileInputStream);
             InputStreamReader inputStreamReader = new InputStreamReader(gzipInputStream);
             BufferedReader reader = new BufferedReader(inputStreamReader)) {

            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(DmsSysElement[].class, new DmsSysArrayElementDeserializer())
                    .registerTypeAdapter(Body.class, new BodyDeserializer())
                    .create();



            DmsSysElement [] dmsSysElements = gson.fromJson(reader, DmsSysElement[].class);

            System.out.println(dmsSysElements);


//            Type type = new TypeToken<DmsSysTemplate[]>(){}.getType();
//            DmsSysTemplate[] read = gson.fromJson(reader, type);
//            System.out.println(read);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void readFromGzFile2(String path, String fileName) {

//        String fullName = path + fileName;
//        try {
//            FileInputStream fileInputStream = new FileInputStream(fullName);
//            GZIPInputStream gzipInputStream = null;
//            try {
//                gzipInputStream = new GZIPInputStream(fileInputStream);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            InputStreamReader inputStreamReader = new InputStreamReader(gzipInputStream);
//            BufferedReader reader = new BufferedReader(inputStreamReader);
//            Gson gson = new GsonBuilder().create();
//            Type type = new TypeToken<DmsSysTemplate[]>(){}.getType();
//            DmsSysTemplate[] read = gson.fromJson(reader, type);
//            System.out.println(read);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }

    }

}
