package com.weatherapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * POJO класс для объекта Current Weather из WeatherAPI
 */
public class Current {
    
    @JsonProperty("last_updated_epoch")
    private Long lastUpdatedEpoch;
    
    @JsonProperty("last_updated")
    private String lastUpdated;
    
    @JsonProperty("temp_c")
    private Double temperatureCelsius;
    
    @JsonProperty("temp_f")
    private Double temperatureFahrenheit;
    
    @JsonProperty("is_day")
    private Integer isDay;
    
    @JsonProperty("condition")
    private Condition condition;
    
    @JsonProperty("wind_mph")
    private Double windMph;
    
    @JsonProperty("wind_kph")
    private Double windKph;
    
    @JsonProperty("wind_degree")
    private Integer windDegree;
    
    @JsonProperty("wind_dir")
    private String windDirection;
    
    @JsonProperty("pressure_mb")
    private Double pressureMillibars;
    
    @JsonProperty("pressure_in")
    private Double pressureInches;
    
    @JsonProperty("precip_mm")
    private Double precipitationMm;
    
    @JsonProperty("precip_in")
    private Double precipitationInches;
    
    @JsonProperty("humidity")
    private Integer humidity;
    
    @JsonProperty("cloud")
    private Integer cloudCover;
    
    @JsonProperty("feelslike_c")
    private Double feelsLikeCelsius;
    
    @JsonProperty("feelslike_f")
    private Double feelsLikeFahrenheit;
    
    @JsonProperty("vis_km")
    private Double visibilityKm;
    
    @JsonProperty("vis_miles")
    private Double visibilityMiles;
    
    @JsonProperty("uv")
    private Double uvIndex;
    
    @JsonProperty("gust_mph")
    private Double gustMph;
    
    @JsonProperty("gust_kph")
    private Double gustKph;

    // Конструкторы
    public Current() {}

    // Геттеры и сеттеры
    public Long getLastUpdatedEpoch() {
        return lastUpdatedEpoch;
    }

    public void setLastUpdatedEpoch(Long lastUpdatedEpoch) {
        this.lastUpdatedEpoch = lastUpdatedEpoch;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Double getTemperatureCelsius() {
        return temperatureCelsius;
    }

    public void setTemperatureCelsius(Double temperatureCelsius) {
        this.temperatureCelsius = temperatureCelsius;
    }

    public Double getTemperatureFahrenheit() {
        return temperatureFahrenheit;
    }

    public void setTemperatureFahrenheit(Double temperatureFahrenheit) {
        this.temperatureFahrenheit = temperatureFahrenheit;
    }

    public Integer getIsDay() {
        return isDay;
    }

    public void setIsDay(Integer isDay) {
        this.isDay = isDay;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public Double getWindMph() {
        return windMph;
    }

    public void setWindMph(Double windMph) {
        this.windMph = windMph;
    }

    public Double getWindKph() {
        return windKph;
    }

    public void setWindKph(Double windKph) {
        this.windKph = windKph;
    }

    public Integer getWindDegree() {
        return windDegree;
    }

    public void setWindDegree(Integer windDegree) {
        this.windDegree = windDegree;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(String windDirection) {
        this.windDirection = windDirection;
    }

    public Double getPressureMillibars() {
        return pressureMillibars;
    }

    public void setPressureMillibars(Double pressureMillibars) {
        this.pressureMillibars = pressureMillibars;
    }

    public Double getPressureInches() {
        return pressureInches;
    }

    public void setPressureInches(Double pressureInches) {
        this.pressureInches = pressureInches;
    }

    public Double getPrecipitationMm() {
        return precipitationMm;
    }

    public void setPrecipitationMm(Double precipitationMm) {
        this.precipitationMm = precipitationMm;
    }

    public Double getPrecipitationInches() {
        return precipitationInches;
    }

    public void setPrecipitationInches(Double precipitationInches) {
        this.precipitationInches = precipitationInches;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public Integer getCloudCover() {
        return cloudCover;
    }

    public void setCloudCover(Integer cloudCover) {
        this.cloudCover = cloudCover;
    }

    public Double getFeelsLikeCelsius() {
        return feelsLikeCelsius;
    }

    public void setFeelsLikeCelsius(Double feelsLikeCelsius) {
        this.feelsLikeCelsius = feelsLikeCelsius;
    }

    public Double getFeelsLikeFahrenheit() {
        return feelsLikeFahrenheit;
    }

    public void setFeelsLikeFahrenheit(Double feelsLikeFahrenheit) {
        this.feelsLikeFahrenheit = feelsLikeFahrenheit;
    }

    public Double getVisibilityKm() {
        return visibilityKm;
    }

    public void setVisibilityKm(Double visibilityKm) {
        this.visibilityKm = visibilityKm;
    }

    public Double getVisibilityMiles() {
        return visibilityMiles;
    }

    public void setVisibilityMiles(Double visibilityMiles) {
        this.visibilityMiles = visibilityMiles;
    }

    public Double getUvIndex() {
        return uvIndex;
    }

    public void setUvIndex(Double uvIndex) {
        this.uvIndex = uvIndex;
    }

    public Double getGustMph() {
        return gustMph;
    }

    public void setGustMph(Double gustMph) {
        this.gustMph = gustMph;
    }

    public Double getGustKph() {
        return gustKph;
    }

    public void setGustKph(Double gustKph) {
        this.gustKph = gustKph;
    }

    @Override
    public String toString() {
        return "Current{" +
                "temperatureCelsius=" + temperatureCelsius +
                ", temperatureFahrenheit=" + temperatureFahrenheit +
                ", condition=" + condition +
                ", windMph=" + windMph +
                ", humidity=" + humidity +
                ", cloudCover=" + cloudCover +
                ", lastUpdated='" + lastUpdated + '\'' +
                '}';
    }
} 