package com.dbmsys.util;


import com.dbmsys.template.GoodsItem;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.zip.GZIPInputStream;

//http://www.javenue.info/post/gson-json-api

public class Reader2 {

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
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            GoodsItem[] arrItems = new GoodsItem[3];
            arrItems[0] = new GoodsItem("Samsung", 51200.6f);
            arrItems[1] = new GoodsItem("Lg", 5400.6f);
            arrItems[2] = new GoodsItem("Alcatel", 4500.6f);

            String jsonStr = gson.toJson(arrItems);
            //String jsonStr = new Gson().toJson(arrItems);
            System.out.println(jsonStr);

            Type itemsArrType = new TypeToken<GoodsItem[]>() {}.getType();

            GoodsItem[] arrItemsDes = new Gson().fromJson(jsonStr, itemsArrType);
            System.out.println(Arrays.toString(arrItemsDes));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }



}
