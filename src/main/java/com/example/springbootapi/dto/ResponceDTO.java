package com.example.springbootapi.dto;

import lombok.Data;

import java.util.List;

@Data
public class ResponceDTO {
    List<OpenWeatherMapDTO> list;

    public String getWeather() {
        String weather = this.list.get(0).getWeather().get(0).getMain();
        System.out.println(weather);
        return weather;
    }
    public int getHumidity() {
        int humidity = this.list.get(0).getMain().getHumidity();
        System.out.println(humidity);
        return humidity;
    }
    public double getSpeed() {
        double speed = this.list.get(0).getWind().getSpeed();
        System.out.println(speed);
        return speed;
    }

}