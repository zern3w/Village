package com.example.puttipong.village.manager;

public class ApiUtils {

    private ApiUtils() {
    }

    public static final String BASE_URL = "http://api.app.minlandsby.dk/village/";

    public static APIService getAPIService() {
        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}