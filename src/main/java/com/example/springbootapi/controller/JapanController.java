package com.example.springbootapi.controller;

import com.example.springbootapi.service.RegionDataService;
import com.example.springbootapi.service.WeatherHandleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;


@Controller
public class JapanController {

    @Autowired
    RegionDataService regionService;

    @Autowired
    WeatherHandleService weatherService;

    @RequestMapping("/japan/")
    public String japan(Model model, @RequestParam(name = "cityName", required = false) String cityName) {

        System.out.println(cityName);

        // require then store dto
        regionService.requireApi(LocalDateTime.now());

        model.addAttribute("viewDate", regionService.currentTime());

        model.addAttribute("cityName", regionService.getJapaneseCityName(cityName));

//        model.addAttribute("weather", regionService.getWeather(cityName));
//        model.addAttribute("temp_max", regionService.getTempMax(cityName));
//        model.addAttribute("temp_min", regionService.getTempMin(cityName));
//
//        model.addAttribute("humid", regionService.getHumid(cityName));
//        model.addAttribute("windSpeed", regionService.getWindSpeed(cityName));

//        model.addAttribute("pollen", weatherService.getPollen(cityName));

        return "japan";
    }
}