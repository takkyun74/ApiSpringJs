package com.example.springbootapi.bean;

import com.example.springbootapi.service.APIContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class PropertiesConfiguration {

    @Bean
    public APIContext apiContext() {
        return new APIContext();
    }

}
