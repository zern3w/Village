package com.example.puttipong.village.dao;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class VillageDao implements Serializable {

    public VillageDao() {
    }

    @SerializedName("Id")                           private String id;
    @SerializedName("Address")                      private String address;
    @SerializedName("Latitude")                     private Double latitude;
    @SerializedName("Longitude")                    private Double longitude;
    @SerializedName("VillageName")                  private String villageName;
    @SerializedName("VillageId")                    private String villageId;
    @SerializedName("PointOfInterestName")          private String pointOfInterestName;
    @SerializedName("PointOfInterestCategoryId")    private String pointOfInterestCategoryId;
    @SerializedName("PointOfInterestCategoryColor") private String pointOfInterestCategoryColor;
    @SerializedName("ImageUrl")                     private String imageUrl;
    @SerializedName("Detail")                       private String detail;
    @SerializedName("PhoneNumber")                  private String phoneNumber;
    @SerializedName("Email")                        private String email;
    @SerializedName("Website")                      private Object website;

    private final static long serialVersionUID = -4642063175822212270L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getVillageName() {
        return villageName;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }

    public String getVillageId() {
        return villageId;
    }

    public void setVillageId(String villageId) {
        this.villageId = villageId;
    }

    public String getPointOfInterestName() {
        return pointOfInterestName;
    }

    public void setPointOfInterestName(String pointOfInterestName) {
        this.pointOfInterestName = pointOfInterestName;
    }

    public String getPointOfInterestCategoryId() {
        return pointOfInterestCategoryId;
    }

    public void setPointOfInterestCategoryId(String pointOfInterestCategoryId) {
        this.pointOfInterestCategoryId = pointOfInterestCategoryId;
    }

    public String getPointOfInterestCategoryColor() {
        return pointOfInterestCategoryColor;
    }

    public void setPointOfInterestCategoryColor(String pointOfInterestCategoryColor) {
        this.pointOfInterestCategoryColor = pointOfInterestCategoryColor;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Object getWebsite() {
        return website;
    }

    public void setWebsite(Object website) {
        this.website = website;
    }

}