package com.weatherapi.steps;

import com.weatherapi.client.WeatherApiClient;
import com.weatherapi.model.WeatherResponse;
import com.weatherapi.model.ErrorResponse;
import io.cucumber.java.ru.*;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

/**
 * Step Definitions для Cucumber тестов Weather API
 */
public class WeatherApiSteps {
    
    private static final Logger logger = LoggerFactory.getLogger(WeatherApiSteps.class);
    
    private WeatherApiClient weatherApiClient;
    private Response lastResponse;
    private WeatherResponse weatherResponse;
    private ErrorResponse errorResponse;
    private String currentApiKey;
    
    @Дано("сервис WeatherAPI запущен на порту {int}")
    public void сервисWeatherAPIЗапущенНаПорту(int port) {
        String baseUrl = "http://localhost:" + port;
        this.weatherApiClient = new WeatherApiClient(baseUrl, "");
        logger.info("Инициализирован клиент WeatherAPI с базовым URL: {}", baseUrl);
    }
    
    @И("у меня есть валидный API ключ {string}")
    public void уМеняЕстьВалидныйAPIКлюч(String apiKey) {
        this.currentApiKey = apiKey;
        this.weatherApiClient = new WeatherApiClient(weatherApiClient.getBaseUrl(), apiKey);
        logger.info("Установлен API ключ для тестирования");
    }
    
    @Когда("я запрашиваю текущую погоду для города {string}")
    public void яЗапрашиваюТекущуюПогодуДляГорода(String city) {
        logger.info("Запрос текущей погоды для города: {}", city);
        this.lastResponse = weatherApiClient.getCurrentWeather(city);
        
        if (lastResponse.getStatusCode() == 200) {
            this.weatherResponse = weatherApiClient.parseWeatherResponse(lastResponse);
        }
    }
    
    @Когда("я запрашиваю текущую погоду без API ключа для города {string}")
    public void яЗапрашиваюТекущуюПогодуБезAPIКлючаДляГорода(String city) {
        logger.info("Запрос текущей погоды без API ключа для города: {}", city);
        this.lastResponse = weatherApiClient.getCurrentWeatherWithoutKey(city);
        
        if (lastResponse.getStatusCode() != 200) {
            this.errorResponse = weatherApiClient.parseErrorResponse(lastResponse);
        }
    }
    
    @Когда("я запрашиваю текущую погоду без параметра города")
    public void яЗапрашиваюТекущуюПогодуБезПараметраГорода() {
        logger.info("Запрос текущей погоды без параметра города");
        this.lastResponse = weatherApiClient.getCurrentWeatherWithoutCity();
        
        if (lastResponse.getStatusCode() != 200) {
            this.errorResponse = weatherApiClient.parseErrorResponse(lastResponse);
        }
    }
    
    @Когда("я запрашиваю текущую погоду с невалидным API ключом {string} для города {string}")
    public void яЗапрашиваюТекущуюПогодуСНевалиднымAPIКлючомДляГорода(String invalidKey, String city) {
        logger.info("Запрос текущей погоды с невалидным API ключом для города: {}", city);
        this.lastResponse = weatherApiClient.getCurrentWeatherWithInvalidKey(city, invalidKey);
        
        if (lastResponse.getStatusCode() != 200) {
            this.errorResponse = weatherApiClient.parseErrorResponse(lastResponse);
        }
    }
    
    @Когда("я запрашиваю текущую погоду для несуществующего города {string}")
    public void яЗапрашиваюТекущуюПогодуДляНесуществующегоГорода(String nonExistentCity) {
        logger.info("Запрос текущей погоды для несуществующего города: {}", nonExistentCity);
        this.lastResponse = weatherApiClient.getCurrentWeatherForNonExistentCity(nonExistentCity);
        
        if (lastResponse.getStatusCode() != 200) {
            this.errorResponse = weatherApiClient.parseErrorResponse(lastResponse);
        }
    }
    
    @Тогда("статус ответа должен быть {int}")
    public void статусОтветаДолженБыть(int expectedStatusCode) {
        int actualStatusCode = lastResponse.getStatusCode();
        logger.info("Ожидаемый статус: {}, Фактический статус: {}", expectedStatusCode, actualStatusCode);
        
        Assert.assertEquals(actualStatusCode, expectedStatusCode, 
            "Статус ответа не соответствует ожидаемому");
    }
    
    @И("ответ содержит информацию о местоположении")
    public void ответСодержитИнформациюОМестоположении() {
        Assert.assertNotNull(weatherResponse, "Weather response не должен быть null");
        Assert.assertNotNull(weatherResponse.getLocation(), "Location не должен быть null");
        logger.info("Ответ содержит информацию о местоположении: {}", weatherResponse.getLocation());
    }
    
    @И("название города в ответе равно {string}")
    public void названиеГородаВОтветеРавно(String expectedCity) {
        String actualCity = weatherResponse.getLocation().getName();
        logger.info("Ожидаемый город: {}, Фактический город: {}", expectedCity, actualCity);
        
        Assert.assertEquals(actualCity, expectedCity, "Название города не соответствует ожидаемому");
    }
    
    @И("страна в ответе равна {string}")
    public void странаВОтветеРавна(String expectedCountry) {
        String actualCountry = weatherResponse.getLocation().getCountry();
        logger.info("Ожидаемая страна: {}, Фактическая страна: {}", expectedCountry, actualCountry);
        
        Assert.assertEquals(actualCountry, expectedCountry, "Страна не соответствует ожидаемой");
    }
    
    @И("ответ содержит данные о текущей погоде")
    public void ответСодержитДанныеОТекущейПогоде() {
        Assert.assertNotNull(weatherResponse.getCurrent(), "Current weather данные не должны быть null");
        logger.info("Ответ содержит данные о текущей погоде: {}", weatherResponse.getCurrent());
    }
    
    @И("температура в Цельсиях присутствует в ответе")
    public void температураВЦельсияхПрисутствуетВОтвете() {
        Double tempC = weatherResponse.getCurrent().getTemperatureCelsius();
        Assert.assertNotNull(tempC, "Температура в Цельсиях не должна быть null");
        logger.info("Температура в Цельсиях: {}°C", tempC);
    }
    
    @И("влажность присутствует в ответе")
    public void влажностьПрисутствуетВОтвете() {
        Integer humidity = weatherResponse.getCurrent().getHumidity();
        Assert.assertNotNull(humidity, "Влажность не должна быть null");
        Assert.assertTrue(humidity >= 0 && humidity <= 100, "Влажность должна быть от 0 до 100%");
        logger.info("Влажность: {}%", humidity);
    }
    
    @И("информация о ветре присутствует в ответе")
    public void информацияОВетреПрисутствуетВОтвете() {
        Double windSpeed = weatherResponse.getCurrent().getWindKph();
        String windDirection = weatherResponse.getCurrent().getWindDirection();
        
        Assert.assertNotNull(windSpeed, "Скорость ветра не должна быть null");
        Assert.assertNotNull(windDirection, "Направление ветра не должно быть null");
        logger.info("Скорость ветра: {} км/ч, Направление: {}", windSpeed, windDirection);
    }
    
    @И("ответ содержит ошибку с кодом {int}")
    public void ответСодержитОшибкуСКодом(int expectedErrorCode) {
        Assert.assertNotNull(errorResponse, "Error response не должен быть null");
        Assert.assertNotNull(errorResponse.getError(), "Error details не должны быть null");
        
        Integer actualErrorCode = errorResponse.getError().getCode();
        logger.info("Ожидаемый код ошибки: {}, Фактический код ошибки: {}", expectedErrorCode, actualErrorCode);
        
        Assert.assertEquals(actualErrorCode.intValue(), expectedErrorCode, 
            "Код ошибки не соответствует ожидаемому");
    }
    
    @И("сообщение об ошибке равно {string}")
    public void сообщениеОбОшибкеРавно(String expectedMessage) {
        String actualMessage = errorResponse.getError().getMessage();
        logger.info("Ожидаемое сообщение: {}, Фактическое сообщение: {}", expectedMessage, actualMessage);
        
        Assert.assertEquals(actualMessage, expectedMessage, 
            "Сообщение об ошибке не соответствует ожидаемому");
    }
    
    @И("я логирую результат сравнения")
    public void яЛогируюРезультатСравнения() {
        logger.info("=== РЕЗУЛЬТАТ СРАВНЕНИЯ УСПЕШНОГО ОТВЕТА ===");
        logger.info("Статус ответа: {}", lastResponse.getStatusCode());
        logger.info("Время ответа: {} мс", lastResponse.getTime());
        
        if (weatherResponse != null) {
            logger.info("Город: {} ({})", 
                weatherResponse.getLocation().getName(),
                weatherResponse.getLocation().getCountry());
            logger.info("Температура: {}°C / {}°F", 
                weatherResponse.getCurrent().getTemperatureCelsius(),
                weatherResponse.getCurrent().getTemperatureFahrenheit());
            logger.info("Влажность: {}%", weatherResponse.getCurrent().getHumidity());
            logger.info("Ветер: {} км/ч, направление {}", 
                weatherResponse.getCurrent().getWindKph(),
                weatherResponse.getCurrent().getWindDirection());
            logger.info("Описание погоды: {}", 
                weatherResponse.getCurrent().getCondition().getText());
        }
        logger.info("=======================================");
    }
    
    @И("я логирую результат сравнения ошибки")
    public void яЛогируюРезультатСравненияОшибки() {
        logger.info("=== РЕЗУЛЬТАТ СРАВНЕНИЯ ОШИБКИ ===");
        logger.info("Статус ответа: {}", lastResponse.getStatusCode());
        logger.info("Время ответа: {} мс", lastResponse.getTime());
        
        if (errorResponse != null) {
            logger.info("Код ошибки: {}", errorResponse.getError().getCode());
            logger.info("Сообщение об ошибке: {}", errorResponse.getError().getMessage());
        }
        logger.info("================================");
    }
} 