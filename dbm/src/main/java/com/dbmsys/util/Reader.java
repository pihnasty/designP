package com.dbmsys.util;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.zip.GZIPInputStream;

//http://www.javenue.info/post/gson-json-api

public class Reader {

    public void readGzFiles(String path, String fileName) {

        String fullName = path + fileName;
        try {
            FileInputStream fileInputStream = new FileInputStream(fullName);
            GZIPInputStream gzipInputStream = null;
            try {
                gzipInputStream = new GZIPInputStream(fileInputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            InputStreamReader inputStreamReader = new InputStreamReader(gzipInputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            Gson gson = new GsonBuilder().create();
            Type type = new TypeToken<Map<String, Integer>>(){}.getType();
            Map<String, Integer> read = gson.fromJson(reader, type);
            System.out.println(read);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }



}
