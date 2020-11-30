package com.example.springbootapi.service;

import org.springframework.stereotype.Component;

@Component
public class JudgeHumid {

    public static boolean isMatch(int humid) {
        return (humid >= 40 && humid <= 80);
    }
}
