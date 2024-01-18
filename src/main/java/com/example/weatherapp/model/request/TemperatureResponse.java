package com.example.weatherapp.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TemperatureResponse {

    private TemperatureStatus status;
    private WeatherDataResponse response;
}
