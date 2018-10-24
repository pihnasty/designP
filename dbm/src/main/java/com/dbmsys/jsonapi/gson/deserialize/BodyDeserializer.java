package com.dbmsys.jsonapi.gson.deserialize;

import com.dbmsys.jsonapi.template.Body;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.Objects;

public class BodyDeserializer implements JsonDeserializer<Body> {
    @Override
    public Body deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        JsonObject jsonObject = json.getAsJsonObject();

        Body body = new Body();
        body.setCategory(Objects.nonNull(jsonObject.get("Category")) ? jsonObject.get("Category").getAsString() : "");
        body.setCounter(Objects.nonNull(jsonObject.get("Counter")) ? jsonObject.get("Counter").getAsString() : "");
        body.setValue(Objects.nonNull(jsonObject.get("Value")) ? jsonObject.get("Value").getAsString() : "");
        body.setStatus(Objects.nonNull(jsonObject.get("Status")) ? jsonObject.get("Status").getAsString() : "");

        return body;
    }
}
