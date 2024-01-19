package com.example.weatherapp.service;

import com.example.weatherapp.model.Main;
import com.example.weatherapp.model.WeatherData;
import com.example.weatherapp.model.request.TemperatureResponse;
import com.example.weatherapp.model.request.TemperatureStatus;
import com.example.weatherapp.model.request.WeatherDataResponse;
import com.example.weatherapp.service.impl.WeatherServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

public class WeatherServiceImplTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private WeatherServiceImpl weatherService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetWeatherSuccess() {
        WeatherData weatherData = new WeatherData();
        Main main = new Main();
        main.setTemp(25.5);
        weatherData.setMain(main);

        Mockito.when(restTemplate.getForObject(any(String.class), eq(WeatherData.class))).thenReturn(weatherData);

        TemperatureResponse temperatureResponse = weatherService.getWeather("City", "metric");

        assertNotNull(temperatureResponse);
        assertEquals(TemperatureStatus.SUCCESS, temperatureResponse.getStatus());

        WeatherDataResponse weatherDataResponse = temperatureResponse.getResponse();
        assertNotNull(weatherDataResponse);
        assertEquals("City", weatherDataResponse.getName());
        assertEquals("25", weatherDataResponse.getTemperature());
        assertEquals("Celsius", weatherDataResponse.getUnit());
    }

    @Test
    public void testGetWeatherCityNotFound() {
        Mockito.when(restTemplate.getForObject(any(String.class), eq(WeatherData.class)))
                .thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND));

        TemperatureResponse temperatureResponse = weatherService.getWeather("NonExistentCity", "metric");

        assertNotNull(temperatureResponse);
        assertEquals(TemperatureStatus.FAILURE, temperatureResponse.getStatus());
    }

    @Test
    public void testGetWeatherApiError() {
        Mockito.when(restTemplate.getForObject(any(String.class), eq(WeatherData.class)))
                .thenThrow(new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR));

        TemperatureResponse temperatureResponse = weatherService.getWeather("City", "metric");

        assertNotNull(temperatureResponse);
        assertEquals(TemperatureStatus.FAILURE, temperatureResponse.getStatus());
    }
}