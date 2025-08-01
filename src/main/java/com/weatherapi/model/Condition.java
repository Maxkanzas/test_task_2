package com.weatherapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * POJO класс для объекта Weather Condition из WeatherAPI
 */
public class Condition {
    
    @JsonProperty("text")
    private String text;
    
    @JsonProperty("icon")
    private String icon;
    
    @JsonProperty("code")
    private Integer code;

    // Конструкторы
    public Condition() {}

    public Condition(String text, String icon, Integer code) {
        this.text = text;
        this.icon = icon;
        this.code = code;
    }

    // Геттеры и сеттеры
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Condition{" +
                "text='" + text + '\'' +
                ", icon='" + icon + '\'' +
                ", code=" + code +
                '}';
    }
} 