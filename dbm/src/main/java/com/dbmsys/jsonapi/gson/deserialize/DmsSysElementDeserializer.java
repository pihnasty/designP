package com.dbmsys.jsonapi.gson.deserialize;

import com.google.gson.*;

import java.lang.reflect.Type;

public class DmsSysElementDeserializer implements JsonDeserializer<String>
{
	@Override
	public String deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws
			JsonParseException
	{
		JsonObject jsonObject = json.getAsJsonObject();



		return "";
	}
}
