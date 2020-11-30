package com.example.springbootapi.service;

import org.springframework.stereotype.Component;

@Component
public class JudgeWeather {

    private static final String IS_MATCH = "Clear";

    public static boolean isMatch(String w) {
        return w.equals(IS_MATCH);
    }
}
