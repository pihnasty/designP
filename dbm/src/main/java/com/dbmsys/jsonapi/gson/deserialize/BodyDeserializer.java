package com.dbmsys.jsonapi.gson.deserialize;

import com.dbmsys.jsonapi.template.Body;
import com.google.gson.*;

import java.lang.reflect.Type;

public class BodyDeserializer implements JsonDeserializer<Body>
{
	@Override
	public Body deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException	{

		Body body = new Body();

		JsonObject jsonObject = json.getAsJsonObject();

		String category = jsonObject.get("Category").getAsString();

		body.setCategory(category);

		return body;
	}
}
