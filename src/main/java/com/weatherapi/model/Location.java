package com.weatherapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * POJO класс для объекта Location из WeatherAPI
 */
public class Location {
    
    @JsonProperty("lat")
    private Double latitude;
    
    @JsonProperty("lon")
    private Double longitude;
    
    @JsonProperty("name")
    private String name;
    
    @JsonProperty("region")
    private String region;
    
    @JsonProperty("country")
    private String country;
    
    @JsonProperty("tz_id")
    private String timezoneId;
    
    @JsonProperty("localtime_epoch")
    private Long localtimeEpoch;
    
    @JsonProperty("localtime")
    private String localtime;

    // Конструкторы
    public Location() {}

    public Location(String name, String region, String country, Double latitude, Double longitude) {
        this.name = name;
        this.region = region;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // Геттеры и сеттеры
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTimezoneId() {
        return timezoneId;
    }

    public void setTimezoneId(String timezoneId) {
        this.timezoneId = timezoneId;
    }

    public Long getLocaltimeEpoch() {
        return localtimeEpoch;
    }

    public void setLocaltimeEpoch(Long localtimeEpoch) {
        this.localtimeEpoch = localtimeEpoch;
    }

    public String getLocaltime() {
        return localtime;
    }

    public void setLocaltime(String localtime) {
        this.localtime = localtime;
    }

    @Override
    public String toString() {
        return "Location{" +
                "name='" + name + '\'' +
                ", region='" + region + '\'' +
                ", country='" + country + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", timezoneId='" + timezoneId + '\'' +
                ", localtime='" + localtime + '\'' +
                '}';
    }
} 