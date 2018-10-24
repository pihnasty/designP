package com.dbmsys.jsonapi.gson.deserialize;

import com.dbmsys.jsonapi.template.Body;
import com.dbmsys.jsonapi.template.DbmsysJsonConstant;
import com.dbmsys.jsonapi.template.DmsSysElement;
import com.dbmsys.jsonapi.template.Head;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DmsSysArrayElementDeserializer implements JsonDeserializer<List<DmsSysElement>> {
    @Override

    public List<DmsSysElement> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws
        JsonParseException {

        JsonArray jsonArray = json.getAsJsonArray();
        int size = jsonArray.size();
        List<DmsSysElement> dmsSysElements = new ArrayList<>();

//        for (int i = 0; i < size; i++) {
//            dmsSysElements.add( context.deserialize(jsonArray.get(i), DmsSysElement.class));
//            dmsSysElements.get(i).setBodies(
//                context.deserialize(jsonArray.get(i).getAsJsonObject().get(DbmsysJsonConstant.NodeNames.BODY), Body[].class)
//            );
//            dmsSysElements.get(i).setHead(
//                context.deserialize(jsonArray.get(i).getAsJsonObject().get(DbmsysJsonConstant.NodeNames.HEAD), Head.class)
//            );
//        }


        jsonArray.forEach(element -> {
            DmsSysElement dmsSysElement = context.deserialize(element, DmsSysElement.class);
            dmsSysElements.add( dmsSysElement);
            dmsSysElement.setBodies(
                context.deserialize(element.getAsJsonObject().get(DbmsysJsonConstant.NodeNames.BODY), Body[].class)
            );
            dmsSysElement.setHead(
                context.deserialize(element.getAsJsonObject().get(DbmsysJsonConstant.NodeNames.HEAD), Head.class)
            );

        });


        return dmsSysElements;
    }
}
