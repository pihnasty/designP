package com.dbmsys.jsonapi.io;

import com.dbmsys.jsonapi.gson.deserialize.BodyDeserializer;
import com.dbmsys.jsonapi.gson.deserialize.DmsSysArrayElementDeserializer;
import com.dbmsys.jsonapi.gson.deserialize.HeadDeserializer;
import com.dbmsys.jsonapi.template.Body;
import com.dbmsys.jsonapi.template.DmsSysElement;
import com.dbmsys.jsonapi.template.Head;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;
import java.util.zip.GZIPInputStream;

//http://www.javenue.info/post/gson-json-api

public class Reader {

    public void readFromGzFile(String path, String fileName) {

        Type type = new TypeToken<List<DmsSysElement>>(){}.getType();

        String fullName = path + fileName;
        try (FileInputStream fileInputStream = new FileInputStream(fullName);
             GZIPInputStream gzipInputStream = new GZIPInputStream(fileInputStream);
             InputStreamReader inputStreamReader = new InputStreamReader(gzipInputStream);
             BufferedReader reader = new BufferedReader(inputStreamReader)) {

            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(type, new DmsSysArrayElementDeserializer())
                    .registerTypeAdapter(Body.class, new BodyDeserializer())
                    .registerTypeAdapter(Head.class, new HeadDeserializer())
                    .create();




            List<DmsSysElement> dmsSysElements = gson.fromJson(reader, type);

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
