package com.example.springbootapi.dto;

import lombok.Data;

@Data
public class Humid {
    int humidity;
    double temp_min;
    double temp_max;
}
