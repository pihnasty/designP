package com.dbmsys.jsonapi.gson.deserialize;

import com.dbmsys.jsonapi.template.Body;
import com.dbmsys.jsonapi.template.DmsSysElement;
import com.google.gson.*;

import java.lang.reflect.Type;

public class DmsSysArrayElementDeserializer implements JsonDeserializer<DmsSysElement []> {
	@Override

	public DmsSysElement[] deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws
			JsonParseException {

        JsonArray jsonArray = json.getAsJsonArray();
        int size = jsonArray.size();
        DmsSysElement[] dmsSysElements = new DmsSysElement [size];


        for (int i=0; i<size; i++) {

            dmsSysElements[i] = context.deserialize( jsonArray.get(i), DmsSysElement.class);

            Body[] bodies ;

            bodies = context.deserialize(jsonArray.get(i).getAsJsonObject().get("Body"), Body[].class);

            dmsSysElements[i].setBodies(bodies);

            dmsSysElements[i].setHead("#"+i);


        }



		return  dmsSysElements ;
	}
}
