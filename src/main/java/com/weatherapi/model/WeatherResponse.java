package com.weatherapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * POJO класс для основного ответа Current Weather API из WeatherAPI
 */
public class WeatherResponse {
    
    @JsonProperty("location")
    private Location location;
    
    @JsonProperty("current")
    private Current current;

    // Конструкторы
    public WeatherResponse() {}

    public WeatherResponse(Location location, Current current) {
        this.location = location;
        this.current = current;
    }

    // Геттеры и сеттеры
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Current getCurrent() {
        return current;
    }

    public void setCurrent(Current current) {
        this.current = current;
    }

    @Override
    public String toString() {
        return "WeatherResponse{" +
                "location=" + location +
                ", current=" + current +
                '}';
    }
} 