package com.weatherapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * POJO класс для ошибок WeatherAPI
 */
public class ErrorResponse {
    
    @JsonProperty("error")
    private ErrorDetails error;

    // Конструкторы
    public ErrorResponse() {}

    public ErrorResponse(ErrorDetails error) {
        this.error = error;
    }

    // Геттеры и сеттеры
    public ErrorDetails getError() {
        return error;
    }

    public void setError(ErrorDetails error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "error=" + error +
                '}';
    }

    /**
     * Внутренний класс для деталей ошибки
     */
    public static class ErrorDetails {
        
        @JsonProperty("code")
        private Integer code;
        
        @JsonProperty("message")
        private String message;

        // Конструкторы
        public ErrorDetails() {}

        public ErrorDetails(Integer code, String message) {
            this.code = code;
            this.message = message;
        }

        // Геттеры и сеттеры
        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        @Override
        public String toString() {
            return "ErrorDetails{" +
                    "code=" + code +
                    ", message='" + message + '\'' +
                    '}';
        }
    }
} 