package com.prajwal.watsonclient;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.prajwal.watsonclient.alchemydata.AlchemyDataNewsClient;
import com.prajwal.watsonclient.alchemydata.AlchemyDataNewsController;

public class WatsonAPIClient {

    public static void main(String[] args) {
        String[] fields =
                new String[] {AlchemyDataNewsController.FIELD_TITLE, AlchemyDataNewsController.FIELD_URL};

        AlchemyDataNewsController.AlchemyDataNewsBuilder queryBuilder = new AlchemyDataNewsController.AlchemyDataNewsBuilder();
        queryBuilder = queryBuilder.setStartTime("1459468800")
                .setEndTime("1459728000")
                .setReturn(StringUtils.join(fields, ","))
                .setTitle("cricket")
                .setCount(3);

        AlchemyDataNewsClient alchemyDataNewsClient = new AlchemyDataNewsClient(queryBuilder.createAlchemyDataNewsController());
        List<Map<String, Object>> parsedResults = alchemyDataNewsClient.getNews();

        for(Map<String, Object> parsedMap : parsedResults) {
            for (String key : parsedMap.keySet()) {
                System.out.println("Key: " + key + " Value: " + parsedMap.get(key));
            }
        }
    }

}
