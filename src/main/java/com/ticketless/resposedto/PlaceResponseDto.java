package com.ticketless.resposedto;

import java.sql.Time;

public class PlaceResponseDto {

private Long placeId;
    
    private String placeName;
    
    private String placeDescription;
    
    private Time startTime;
    
    private Time endTime;
    
    private String city;
    
    private String state;
    
    private String latitude;
    
    private String longitude;
    
    private String priceAdult;
    
    private String priceChild;
    
    private String picUrl;

    public PlaceResponseDto() {
        super();
    }

    public PlaceResponseDto(Long placeId, String placeName, String placeDescription, Time startTime, Time endTime,
            String city, String state, String latitude, String longitude, String priceAdult, String priceChild,
            String picUrl) {
        super();
        this.placeId = placeId;
        this.placeName = placeName;
        this.placeDescription = placeDescription;
        this.startTime = startTime;
        this.endTime = endTime;
        this.city = city;
        this.state = state;
        this.latitude = latitude;
        this.longitude = longitude;
        this.priceAdult = priceAdult;
        this.priceChild = priceChild;
        this.picUrl = picUrl;
    }

    public Long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getPlaceDescription() {
        return placeDescription;
    }

    public void setPlaceDescription(String placeDescription) {
        this.placeDescription = placeDescription;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getPriceAdult() {
        return priceAdult;
    }

    public void setPriceAdult(String priceAdult) {
        this.priceAdult = priceAdult;
    }

    public String getPriceChild() {
        return priceChild;
    }

    public void setPriceChild(String priceChild) {
        this.priceChild = priceChild;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    @Override
    public String toString() {
        return "PlaceResponseDto [placeId=" + placeId + ", placeName=" + placeName + ", placeDescription="
                + placeDescription + ", startTime=" + startTime + ", endTime=" + endTime + ", city=" + city + ", state="
                + state + ", latitude=" + latitude + ", longitude=" + longitude + ", priceAdult=" + priceAdult
                + ", priceChild=" + priceChild + ", picUrl=" + picUrl + "]";
    }

}
