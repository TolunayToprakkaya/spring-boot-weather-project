package com.example.weatherapp.controller;

import com.example.weatherapp.model.request.TemperatureRequest;
import com.example.weatherapp.service.WeatherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("api/weather")
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @RequestMapping(value = "/temperature", method = { RequestMethod.GET, RequestMethod.POST })
    public ResponseEntity<?> getTemperature(@RequestParam(required = false) String cityName,
                                         @RequestParam(required = false) String unit,
                                         @RequestBody(required = false) TemperatureRequest request) {
        if (Objects.isNull(request)) {
            return ResponseEntity.ok(weatherService.getWeather(cityName, unit));
        }
        return ResponseEntity.ok(weatherService.getWeather(request.getCityName(), request.getUnit()));
    }
}
