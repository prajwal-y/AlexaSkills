package com.prajwal.watsonclient.alchemydata;

import java.util.HashMap;
import java.util.Map;

import com.ibm.watson.developer_cloud.alchemy.v1.AlchemyDataNews;
import com.ibm.watson.developer_cloud.alchemy.v1.model.DocumentsResult;

public class AlchemyDataNewsController extends AlchemyDataNews {

    /* Fields */
    public static final String FIELD_TITLE = "enriched.url.title";
    public static final String FIELD_URL = "enriched.url.url";
    public static final String FIELD_AUTHOR = "enriched.url.author";
    public static final String FIELD_PUB_DATE = "enriched.url.publicationDate";
    public static final String FIELD_ENTITIES = "enriched.url.enrichedTitle.entities";
    public static final String FIELD_SENTIMENT = "enriched.url.enrichedTitle.docSentiment";

    /* Params */
    private static final String COUNT = "count";
    private static final String END = "end";
    private static final String RETURN = "return";
    private static final String START = "start";
    private static final String TIME_SLICE = "timeSlice";
    private static final String TITLE = "q.enriched.url.title";
    private static final String AUTHOR = "q.enriched.url.author";

    private Map<String, Object> queryParams;

    private AlchemyDataNewsController() {
        queryParams = new HashMap<String, Object>();
    }

    public static class AlchemyDataNewsBuilder {

        private final AlchemyDataNewsController alchemyDataNewsController;

        public AlchemyDataNewsBuilder() {
            alchemyDataNewsController = new AlchemyDataNewsController();
        }

        public AlchemyDataNewsBuilder setStartTime(String startTime) {
            alchemyDataNewsController.queryParams.put(START, startTime);
            return this;
        }

        public AlchemyDataNewsBuilder setEndTime(String endTime) {
            alchemyDataNewsController.queryParams.put(END, endTime);
            return this;
        }

        public AlchemyDataNewsBuilder setReturn(String returnFields) {
            alchemyDataNewsController.queryParams.put(RETURN, returnFields);
            return this;
        }

        public AlchemyDataNewsBuilder setTitle(String title) {
            alchemyDataNewsController.queryParams.put(TITLE, title);
            return this;
        }

        public AlchemyDataNewsBuilder setCount(int count) {
            alchemyDataNewsController.queryParams.put(COUNT, count);
            return this;
        }

        public AlchemyDataNewsBuilder setAuthor(String title) {
            alchemyDataNewsController.queryParams.put(AUTHOR, title);
            return this;
        }

        public AlchemyDataNewsController createAlchemyDataNewsController() {
            return alchemyDataNewsController;
        }

    }

    public DocumentsResult getNewsDocuments() {
        return super.getNewsDocuments(queryParams);
    }

}
