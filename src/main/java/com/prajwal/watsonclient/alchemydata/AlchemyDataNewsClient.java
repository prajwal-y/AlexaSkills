package com.prajwal.watsonclient.alchemydata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ibm.watson.developer_cloud.alchemy.v1.model.DocumentsResult;
import com.prajwal.watsonclient.util.WatsonClientConfig;

public class AlchemyDataNewsClient {

    private AlchemyDataNewsController alchemyDataNewsController;

    public AlchemyDataNewsClient(AlchemyDataNewsController alchemyDataNewsController) {
        this.alchemyDataNewsController = alchemyDataNewsController;
    }

    public List<Map<String, Object>> getNews() {
        if(alchemyDataNewsController == null) {
            throw new IllegalStateException("Looks like you haven't initialized the controller correctly");
        }
        alchemyDataNewsController.setApiKey(WatsonClientConfig.getApiKey());
        DocumentsResult result = alchemyDataNewsController.getNewsDocuments();
        return parseResult(result.toString());
    }

    private List<Map<String, Object>> parseResult(String jsonResult) {
        JsonObject jsonObject = new JsonParser().parse(jsonResult).getAsJsonObject();
        List<Map<String, Object>> parsedList = new ArrayList<Map<String, Object>>();
        if(jsonObject == null || (jsonObject.get("result") == null)) {
            return parsedList;
        }
        JsonObject sourceObject = jsonObject.get("result").getAsJsonObject();
        if(sourceObject.get("docs") == null) {
            return parsedList;
        }
        JsonArray docArray = sourceObject.get("docs").getAsJsonArray();
        System.out.println("Size is " + docArray.size());
        for (JsonElement doc : docArray) {
            if(doc.getAsJsonObject().get("source") == null ||
                    doc.getAsJsonObject().get("source").getAsJsonObject().get("enriched") == null) {
                continue;
            }
            JsonObject enrichedObject = doc.getAsJsonObject().get("source").getAsJsonObject().get("enriched").getAsJsonObject();
            Map<String, Object> parsedMap = new HashMap<String, Object>();
            if(enrichedObject.get("url") != null) {
                parsedMap.put("title", enrichedObject.get("url").getAsJsonObject().get("title"));
                parsedMap.put("url", enrichedObject.get("url").getAsJsonObject().get("url"));
            }
            parsedList.add(parsedMap);
        }
        return parsedList;
    }

}