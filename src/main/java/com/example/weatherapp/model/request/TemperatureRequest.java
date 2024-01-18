package com.example.weatherapp.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TemperatureRequest {

    private String cityName;
    private String unit;
}
