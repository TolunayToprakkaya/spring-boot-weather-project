package com.example.weatherapp.model.request;

import lombok.Getter;

@Getter
public enum TemperatureStatus {

    SUCCESS(1),
    FAILURE(2);

    final int statusId;

    TemperatureStatus(int statusId) {
        this.statusId = statusId;
    }
}
