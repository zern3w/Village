package com.example.puttipong.village.manager;

import com.example.puttipong.village.dao.VillageDao;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {

    @GET("GetPointOfInterestDetail?pointOfInterestId=ea176d83-5758-e511-810a-0a000fa90c6f&language=da")
    Call<VillageDao> getVillage();

    @GET("GetAllPointOfInterests?villageId=bec7f9a1-fe57-e511-810a-0a000fa90c6f")
    Call<List<VillageDao>> getVillageList();

}