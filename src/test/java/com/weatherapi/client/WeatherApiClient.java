package com.weatherapi.client;

import com.weatherapi.model.WeatherResponse;
import com.weatherapi.model.ErrorResponse;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * HTTP клиент для работы с WeatherAPI
 */
public class WeatherApiClient {
    
    private static final Logger logger = LoggerFactory.getLogger(WeatherApiClient.class);
    
    private final String baseUrl;
    private final String apiKey;
    
    public WeatherApiClient(String baseUrl, String apiKey) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
        
        // Настройка RestAssured
        RestAssured.baseURI = baseUrl;
    }
    
    /**
     * Получить текущую погоду для указанного города
     * @param city название города
     * @return Response объект
     */
    public Response getCurrentWeather(String city) {
        logger.info("Запрос текущей погоды для города: {}", city);
        
        RequestSpecification request = RestAssured.given()
                .queryParam("key", apiKey)
                .queryParam("q", city)
                .queryParam("aqi", "no");
        
        Response response = request.get("/v1/current.json");
        
        logger.info("Получен ответ: статус {}, время ответа {} ms", 
                   response.getStatusCode(), response.getTime());
        
        return response;
    }
    
    /**
     * Получить текущую погоду без API ключа (для негативного тестирования)
     * @param city название города
     * @return Response объект
     */
    public Response getCurrentWeatherWithoutKey(String city) {
        logger.info("Запрос текущей погоды без API ключа для города: {}", city);
        
        RequestSpecification request = RestAssured.given()
                .queryParam("q", city)
                .queryParam("aqi", "no");
        
        Response response = request.get("/v1/current.json");
        
        logger.info("Получен ответ: статус {}, время ответа {} ms", 
                   response.getStatusCode(), response.getTime());
        
        return response;
    }
    
    /**
     * Получить текущую погоду с невалидным API ключом
     * @param city название города
     * @param invalidKey невалидный API ключ
     * @return Response объект
     */
    public Response getCurrentWeatherWithInvalidKey(String city, String invalidKey) {
        logger.info("Запрос текущей погоды с невалидным ключом для города: {}", city);
        
        RequestSpecification request = RestAssured.given()
                .queryParam("key", invalidKey)
                .queryParam("q", city)
                .queryParam("aqi", "no");
        
        Response response = request.get("/v1/current.json");
        
        logger.info("Получен ответ: статус {}, время ответа {} ms", 
                   response.getStatusCode(), response.getTime());
        
        return response;
    }
    
    /**
     * Получить текущую погоду без параметра города
     * @return Response объект
     */
    public Response getCurrentWeatherWithoutCity() {
        logger.info("Запрос текущей погоды без параметра города");
        
        RequestSpecification request = RestAssured.given()
                .queryParam("key", apiKey)
                .queryParam("aqi", "no");
        
        Response response = request.get("/v1/current.json");
        
        logger.info("Получен ответ: статус {}, время ответа {} ms", 
                   response.getStatusCode(), response.getTime());
        
        return response;
    }
    
    /**
     * Получить текущую погоду для несуществующего города
     * @param nonExistentCity несуществующий город
     * @return Response объект
     */
    public Response getCurrentWeatherForNonExistentCity(String nonExistentCity) {
        logger.info("Запрос текущей погоды для несуществующего города: {}", nonExistentCity);
        
        RequestSpecification request = RestAssured.given()
                .queryParam("key", apiKey)
                .queryParam("q", nonExistentCity)
                .queryParam("aqi", "no");
        
        Response response = request.get("/v1/current.json");
        
        logger.info("Получен ответ: статус {}, время ответа {} ms", 
                   response.getStatusCode(), response.getTime());
        
        return response;
    }
    
    /**
     * Десериализовать ответ в WeatherResponse объект
     * @param response HTTP ответ
     * @return WeatherResponse объект
     */
    public WeatherResponse parseWeatherResponse(Response response) {
        return response.as(WeatherResponse.class);
    }
    
    /**
     * Десериализовать ответ об ошибке в ErrorResponse объект
     * @param response HTTP ответ
     * @return ErrorResponse объект
     */
    public ErrorResponse parseErrorResponse(Response response) {
        return response.as(ErrorResponse.class);
    }
    
    // Геттеры
    public String getBaseUrl() {
        return baseUrl;
    }
    
    public String getApiKey() {
        return apiKey;
    }
} 