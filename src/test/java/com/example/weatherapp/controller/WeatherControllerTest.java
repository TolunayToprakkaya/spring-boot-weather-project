package com.example.weatherapp.controller;

import com.example.weatherapp.model.request.TemperatureRequest;
import com.example.weatherapp.model.request.TemperatureResponse;
import com.example.weatherapp.model.request.TemperatureStatus;
import com.example.weatherapp.service.WeatherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class WeatherControllerTest {

    @Mock
    private WeatherService weatherService;

    @InjectMocks
    private WeatherController weatherController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetTemperatureWithRequestParam() {
        TemperatureResponse temperatureResponse = new TemperatureResponse();
        temperatureResponse.setStatus(TemperatureStatus.SUCCESS);
        Mockito.when(weatherService.getWeather("City", "metric")).thenReturn(temperatureResponse);

        ResponseEntity<?> responseEntity = weatherController.getTemperature("City", "metric", null);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(temperatureResponse, responseEntity.getBody());
    }

    @Test
    public void testGetTemperatureWithRequestBody() {
        TemperatureRequest temperatureRequest = new TemperatureRequest();
        temperatureRequest.setCityName("City");
        temperatureRequest.setUnit("metric");

        TemperatureResponse temperatureResponse = new TemperatureResponse();
        temperatureResponse.setStatus(TemperatureStatus.SUCCESS);
        Mockito.when(weatherService.getWeather("City", "metric")).thenReturn(temperatureResponse);

        ResponseEntity<?> responseEntity = weatherController.getTemperature(null, null, temperatureRequest);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(temperatureResponse, responseEntity.getBody());
    }
}