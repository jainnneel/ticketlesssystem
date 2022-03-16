package com.ticketless.requestdto;

public class StateCityPlaceSearch {

    private String stateName;

    private String cityName;
    
    private String placeName;

    public StateCityPlaceSearch(String stateName, String cityName, String placeName) {
        super();
        this.stateName = stateName;
        this.cityName = cityName;
        this.placeName = placeName;
    }

    public StateCityPlaceSearch() {
        super();
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    @Override
    public String toString() {
        return "StateCityPlaceSearch [stateName=" + stateName + ", cityName=" + cityName + ", placeName=" + placeName
                + "]";
    }
    
}
