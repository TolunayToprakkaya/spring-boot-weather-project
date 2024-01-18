package com.example.weatherapp.service;

import com.example.weatherapp.model.request.TemperatureResponse;

public interface WeatherService {

    TemperatureResponse getWeather(String city, String unit);

}
