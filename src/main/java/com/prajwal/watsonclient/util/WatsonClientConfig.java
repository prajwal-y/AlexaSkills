package com.prajwal.watsonclient.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;

public class WatsonClientConfig {

    private static WatsonClientConfig watsonClientConfig;

    private HashMap<String, String> configParams;

    private WatsonClientConfig() {
        configParams = new HashMap<String, String>();
        initializeConfig();
    }

    private static WatsonClientConfig getWatsonClientConfig() {
        if(watsonClientConfig == null) {
            watsonClientConfig = new WatsonClientConfig();
        }
        return watsonClientConfig;
    }

    private void initializeConfig() {
        ClassLoader classLoader = getClass().getClassLoader();
        BufferedReader bufferedReader;
        try {
            URL resource = classLoader.getResource(WatsonClientConstants.CONFIG_FILE);
            if (resource != null) {
                bufferedReader = new BufferedReader(new FileReader(resource.getFile()));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    String[] values = line.split("=");
                    configParams.put(values[0], values[1]);
                }
                bufferedReader.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getApiKey() {
        return getWatsonClientConfig().configParams.get(WatsonClientConstants.API_KEY);
    }

}
