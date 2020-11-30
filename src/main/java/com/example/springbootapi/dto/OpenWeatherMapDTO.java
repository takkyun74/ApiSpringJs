package com.example.springbootapi.dto;

import lombok.Data;

import java.util.List;

@Data
public class OpenWeatherMapDTO {
    String name;
    int id;
    Humid main;
    List<Weather> weather;
    Wind wind;
}
