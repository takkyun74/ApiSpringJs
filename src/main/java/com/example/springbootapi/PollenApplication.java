package com.example.springbootapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:static/prop/api.properties")
public class PollenApplication {

    public static void main(String[] args) {
        SpringApplication.run(PollenApplication.class, args);
    }

}