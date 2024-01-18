package com.example.weatherapp.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherDataResponse {

    private String name;
    private String temperature;
    private String unit;
}
