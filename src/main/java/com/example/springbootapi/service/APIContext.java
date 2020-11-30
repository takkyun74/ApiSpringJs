package com.example.springbootapi.service;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix="api")
public class APIContext {

    private String key;
}