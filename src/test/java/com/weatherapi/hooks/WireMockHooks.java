package com.weatherapi.hooks;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static com.github.tomakehurst.wiremock.client.WireMock.*;

/**
 * Hooks для управления WireMock сервером
 */
public class WireMockHooks {
    
    private static final Logger logger = LoggerFactory.getLogger(WireMockHooks.class);
    private static final int WIREMOCK_PORT = 8080;
    
    private static WireMockServer wireMockServer;
    
    @Before
    public void startWireMock() {
                if (wireMockServer == null || !wireMockServer.isRunning()) {
            logger.info("Запуск WireMock сервера на порту {}", WIREMOCK_PORT);

            wireMockServer = new WireMockServer(
                WireMockConfiguration.wireMockConfig()
                    .port(WIREMOCK_PORT)
            );

            wireMockServer.start();
            
            // Программная настройка mappings
            setupMappings();
            
            logger.info("WireMock сервер запущен успешно с программными mappings");
        }
    }
    
    @After
    public void stopWireMock() {
        if (wireMockServer != null && wireMockServer.isRunning()) {
            logger.info("Остановка WireMock сервера");
            wireMockServer.stop();
            logger.info("WireMock сервер остановлен");
        }
    }
    
    private void setupMappings() {
        logger.info("Настройка программных mappings...");
        
        // Позитивные тесты для городов (средний приоритет)
        wireMockServer.stubFor(get(urlPathEqualTo("/v1/current.json"))
            .atPriority(2)
            .withQueryParam("q", equalTo("London"))
            .withQueryParam("key", matching(".*"))
            .withQueryParam("aqi", equalTo("no"))
            .willReturn(aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBody("{\"location\":{\"name\":\"London\",\"region\":\"City of London, Greater London\",\"country\":\"United Kingdom\",\"lat\":51.52,\"lon\":-0.11,\"tz_id\":\"Europe/London\",\"localtime_epoch\":1735735200,\"localtime\":\"2025-01-01 12:00\"},\"current\":{\"last_updated_epoch\":1735735200,\"last_updated\":\"2025-01-01 12:00\",\"temp_c\":8.5,\"temp_f\":47.3,\"is_day\":1,\"condition\":{\"text\":\"Partly cloudy\",\"icon\":\"//cdn.weatherapi.com/weather/64x64/day/116.png\",\"code\":1003},\"wind_mph\":15.2,\"wind_kph\":24.5,\"wind_degree\":220,\"wind_dir\":\"SW\",\"pressure_mb\":1015.0,\"pressure_in\":29.97,\"precip_mm\":0.2,\"precip_in\":0.01,\"humidity\":72,\"cloud\":75,\"feelslike_c\":5.8,\"feelslike_f\":42.4,\"vis_km\":10.0,\"vis_miles\":6.0,\"uv\":2.0,\"gust_mph\":22.4,\"gust_kph\":36.0}}")));

        wireMockServer.stubFor(get(urlPathEqualTo("/v1/current.json"))
            .atPriority(2)
            .withQueryParam("q", equalTo("Paris"))
            .withQueryParam("key", matching(".*"))
            .withQueryParam("aqi", equalTo("no"))
            .willReturn(aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBody("{\"location\":{\"name\":\"Paris\",\"region\":\"Ile-de-France\",\"country\":\"France\",\"lat\":48.87,\"lon\":2.33,\"tz_id\":\"Europe/Paris\",\"localtime_epoch\":1735738800,\"localtime\":\"2025-01-01 13:00\"},\"current\":{\"last_updated_epoch\":1735738800,\"last_updated\":\"2025-01-01 13:00\",\"temp_c\":12.3,\"temp_f\":54.1,\"is_day\":1,\"condition\":{\"text\":\"Sunny\",\"icon\":\"//cdn.weatherapi.com/weather/64x64/day/113.png\",\"code\":1000},\"wind_mph\":8.1,\"wind_kph\":13.0,\"wind_degree\":180,\"wind_dir\":\"S\",\"pressure_mb\":1020.0,\"pressure_in\":30.12,\"precip_mm\":0.0,\"precip_in\":0.0,\"humidity\":65,\"cloud\":25,\"feelslike_c\":11.2,\"feelslike_f\":52.2,\"vis_km\":15.0,\"vis_miles\":9.0,\"uv\":3.0,\"gust_mph\":12.5,\"gust_kph\":20.1}}")));

        wireMockServer.stubFor(get(urlPathEqualTo("/v1/current.json"))
            .atPriority(2)
            .withQueryParam("q", equalTo("Tokyo"))
            .withQueryParam("key", matching(".*"))
            .withQueryParam("aqi", equalTo("no"))
            .willReturn(aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBody("{\"location\":{\"name\":\"Tokyo\",\"region\":\"Tokyo\",\"country\":\"Japan\",\"lat\":35.69,\"lon\":139.69,\"tz_id\":\"Asia/Tokyo\",\"localtime_epoch\":1735768800,\"localtime\":\"2025-01-01 21:00\"},\"current\":{\"last_updated_epoch\":1735768800,\"last_updated\":\"2025-01-01 21:00\",\"temp_c\":3.2,\"temp_f\":37.8,\"is_day\":0,\"condition\":{\"text\":\"Clear\",\"icon\":\"//cdn.weatherapi.com/weather/64x64/night/113.png\",\"code\":1000},\"wind_mph\":5.4,\"wind_kph\":8.6,\"wind_degree\":270,\"wind_dir\":\"W\",\"pressure_mb\":1025.0,\"pressure_in\":30.27,\"precip_mm\":0.0,\"precip_in\":0.0,\"humidity\":58,\"cloud\":10,\"feelslike_c\":1.1,\"feelslike_f\":34.0,\"vis_km\":20.0,\"vis_miles\":12.0,\"uv\":1.0,\"gust_mph\":8.9,\"gust_kph\":14.3}}")));

        wireMockServer.stubFor(get(urlPathEqualTo("/v1/current.json"))
            .atPriority(2)
            .withQueryParam("q", equalTo("New York"))
            .withQueryParam("key", matching(".*"))
            .withQueryParam("aqi", equalTo("no"))
            .willReturn(aResponse()
                .withStatus(200)
                .withHeader("Content-Type", "application/json")
                .withBody("{\"location\":{\"name\":\"New York\",\"region\":\"New York\",\"country\":\"United States of America\",\"lat\":40.71,\"lon\":-74.01,\"tz_id\":\"America/New_York\",\"localtime_epoch\":1735746000,\"localtime\":\"2025-01-01 07:00\"},\"current\":{\"last_updated_epoch\":1735746000,\"last_updated\":\"2025-01-01 07:00\",\"temp_c\":-2.1,\"temp_f\":28.2,\"is_day\":1,\"condition\":{\"text\":\"Light snow\",\"icon\":\"//cdn.weatherapi.com/weather/64x64/day/326.png\",\"code\":1213},\"wind_mph\":18.3,\"wind_kph\":29.5,\"wind_degree\":320,\"wind_dir\":\"NW\",\"pressure_mb\":1008.0,\"pressure_in\":29.77,\"precip_mm\":1.2,\"precip_in\":0.05,\"humidity\":78,\"cloud\":90,\"feelslike_c\":-7.8,\"feelslike_f\":18.0,\"vis_km\":8.0,\"vis_miles\":5.0,\"uv\":1.0,\"gust_mph\":28.4,\"gust_kph\":45.7}}")));

        // Негативные тесты - запрос без параметра города (приоритет 5)
        wireMockServer.stubFor(get(urlPathEqualTo("/v1/current.json"))
            .atPriority(5)
            .withQueryParam("key", matching(".*"))
            .withQueryParam("aqi", equalTo("no"))
            .willReturn(aResponse()
                .withStatus(400)
                .withHeader("Content-Type", "application/json")
                .withBody("{\"error\":{\"code\":1003,\"message\":\"Parameter 'q' not provided.\"}}")));

        // Негативные тесты - запрос без API ключа (низкий приоритет)
        wireMockServer.stubFor(get(urlPathEqualTo("/v1/current.json"))
            .atPriority(10)
            .withQueryParam("q", matching(".*"))
            .withQueryParam("aqi", equalTo("no"))
            .willReturn(aResponse()
                .withStatus(401)
                .withHeader("Content-Type", "application/json")
                .withBody("{\"error\":{\"code\":1002,\"message\":\"API key not provided.\"}}")));

        wireMockServer.stubFor(get(urlPathEqualTo("/v1/current.json"))
            .atPriority(1)
            .withQueryParam("key", equalTo("invalid_key_12345"))
            .withQueryParam("q", matching(".*"))
            .withQueryParam("aqi", equalTo("no"))
            .willReturn(aResponse()
                .withStatus(401)
                .withHeader("Content-Type", "application/json")
                .withBody("{\"error\":{\"code\":2006,\"message\":\"API key provided is invalid\"}}")));

        // Негативные тесты - несуществующий город (приоритет 1)
        wireMockServer.stubFor(get(urlPathEqualTo("/v1/current.json"))
            .atPriority(1)
            .withQueryParam("key", matching(".*"))
            .withQueryParam("q", equalTo("NonExistentCity123"))
            .withQueryParam("aqi", equalTo("no"))
            .willReturn(aResponse()
                .withStatus(400)
                .withHeader("Content-Type", "application/json")
                .withBody("{\"error\":{\"code\":1006,\"message\":\"No location found matching parameter 'q'\"}}")));
        
        logger.info("Программные mappings настроены успешно");
    }

    public static WireMockServer getWireMockServer() {
        return wireMockServer;
    }
} 