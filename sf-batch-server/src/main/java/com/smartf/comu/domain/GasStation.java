package com.smartf.comu.domain;

public class GasStation {
    private Long id;
    private String area;
    private String stationName;
    private Double lat;
    private Double lng;
    private Double gasAmount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Double getGasAmount() {
        return gasAmount;
    }

    public void setGasAmount(Double gasAmount) {
        this.gasAmount = gasAmount;
    }

    public Double getMaxAmount() {
        return MaxAmount;
    }

    public void setMaxAmount(Double maxAmount) {
        MaxAmount = maxAmount;
    }

    private Double MaxAmount;
}
