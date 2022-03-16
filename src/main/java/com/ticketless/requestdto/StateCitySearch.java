package com.ticketless.requestdto;

public class StateCitySearch {

    private String stateName;
    
    private String cityName;
    
    private String placeName;

    public StateCitySearch() {
        super();
    }

    public StateCitySearch(String stateName, String cityName, String placeName) {
        super();
        this.stateName = stateName;
        this.cityName = cityName;
        this.placeName = placeName;
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
        return "StateCitySearch [stateName=" + stateName + ", cityName=" + cityName + ", placeName=" + placeName + "]";
    }
    
}
