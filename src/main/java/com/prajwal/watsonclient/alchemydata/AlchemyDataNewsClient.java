package com.prajwal.watsonclient.alchemydata;

import com.ibm.watson.developer_cloud.alchemy.v1.model.DocumentsResult;
import com.prajwal.watsonclient.util.WatsonClientConfig;

public class AlchemyDataNewsClient {

    private AlchemyDataNewsController alchemyDataNewsController;

    public AlchemyDataNewsClient(AlchemyDataNewsController alchemyDataNewsController) {
        this.alchemyDataNewsController = alchemyDataNewsController;
    }

    public void getNews() {
        if(alchemyDataNewsController == null) {
            throw new IllegalStateException("Looks like you haven't initialized the controller correctly");
        }
        alchemyDataNewsController.setApiKey(WatsonClientConfig.getApiKey());
        DocumentsResult result = alchemyDataNewsController.getNewsDocuments();
        System.out.println(result);
    }

}
