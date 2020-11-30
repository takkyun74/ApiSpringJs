package com.example.springbootapi.service;

import org.springframework.stereotype.Component;

@Component
public class JudgeWindSpeed {

    public static boolean isMatch(double speed) {
        return (speed >= 8.0 && speed <= 10.7);
    }
}
