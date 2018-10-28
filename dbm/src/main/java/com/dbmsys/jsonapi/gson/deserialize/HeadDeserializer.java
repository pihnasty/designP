package com.dbmsys.jsonapi.gson.deserialize;

import com.dbmsys.jsonapi.template.data.Head;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;

public class HeadDeserializer implements JsonDeserializer<Head> {
    @Override
    public Head deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        Head head = new Head();
        jsonObject.entrySet().forEach(parameter -> head.addParameter(parameter.getKey(), parameter.getValue().getAsString()));
        return head;
    }
}
