package com.example.weatherapp.service.impl;

import com.example.weatherapp.model.WeatherData;
import com.example.weatherapp.model.request.TemperatureResponse;
import com.example.weatherapp.model.request.TemperatureStatus;
import com.example.weatherapp.model.request.WeatherDataResponse;
import com.example.weatherapp.service.WeatherService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
public class WeatherServiceImpl implements WeatherService {

    private final static String apiUrl = "https://api.openweathermap.org/data/2.5/weather";
    private final static String apiKey = "af9d8709ee09996da7909c6b3de5ae5c";

    private final RestTemplate restTemplate;

    public WeatherServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public TemperatureResponse getWeather(String city, String unit) {
        TemperatureResponse temperatureResponse = new TemperatureResponse();

        try {
            WeatherData weatherData = getWeatherData(city, unit);

            if (Objects.isNull(weatherData)) {
                temperatureResponse.setStatus(TemperatureStatus.FAILURE);
            } else {
                temperatureResponse.setStatus(TemperatureStatus.SUCCESS);
                WeatherDataResponse weatherDataResponse = new WeatherDataResponse();
                weatherDataResponse.setName(city);

                String temperature = String.valueOf(Math.round((weatherData.getMain().getTemp() % 1.0) > 0.50 ?
                        Math.ceil(weatherData.getMain().getTemp()) :
                        Math.floor(weatherData.getMain().getTemp())));

                weatherDataResponse.setTemperature(temperature);
                weatherDataResponse.setUnit("Celsius");

                temperatureResponse.setResponse(weatherDataResponse);
            }
        } catch (HttpClientErrorException httpClientErrorException) {
            temperatureResponse.setStatus(TemperatureStatus.FAILURE);
        }

        return temperatureResponse;
    }

    private WeatherData getWeatherData(String city, String unit) throws HttpClientErrorException {
        String url = apiUrl + "?q=" + city + "&appid=" + apiKey + "&units=" + unit;
        try {
            return restTemplate.getForObject(url, WeatherData.class);
        } catch (HttpClientErrorException httpClientErrorException) {
            if (HttpStatus.NOT_FOUND.equals(httpClientErrorException.getStatusCode())) {
                System.out.println("Couldn't find city: " + city);
                throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
            } else {
                System.out.println("Couldn't get data from OpenWeatherMap API.");
                throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

}
