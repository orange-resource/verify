package com.orange.verify.api.util;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class GsonUtil {

    public static Gson buildGson() {
        Gson gson = new GsonBuilder().registerTypeAdapter(Map.class, new JsonDeserializer<Map>() {
            @Override
            public Map<String, Object> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                Map<String, Object> resultMap = new HashMap<>();
                JsonObject jsonObject = json.getAsJsonObject();
                Set<Map.Entry<String, JsonElement>> entrySet = jsonObject.entrySet();
                for (Map.Entry<String, JsonElement> entry : entrySet) {
                    resultMap.put(entry.getKey(), entry.getValue());
                }
                return resultMap;
            }

        }).setDateFormat("yyyy-MM-dd' 'HH:mm:ss").serializeNulls().create();
        return gson;
    }

}
