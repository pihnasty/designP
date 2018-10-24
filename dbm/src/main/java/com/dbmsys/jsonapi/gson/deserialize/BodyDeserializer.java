package com.dbmsys.jsonapi.gson.deserialize;

import com.dbmsys.jsonapi.template.Body;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;

public class BodyDeserializer implements JsonDeserializer<Body> {
    @Override
    public Body deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        Body body = new Body();
        jsonObject.entrySet().forEach(parameter -> body.addParameter(parameter.getKey(), parameter.getValue().getAsString()));
        return body;
    }
}
