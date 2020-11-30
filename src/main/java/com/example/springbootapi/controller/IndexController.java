package com.example.springbootapi.controller;

import com.example.springbootapi.service.WeatherHandleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class IndexController {

    @Autowired
    private WeatherHandleService service;

    @RequestMapping("/index/")
    public String index(Model model, @RequestParam(name = "cityName", required = false) String cityName) {

        model.addAttribute("cityName", service.getJapaneseCityName(cityName));
        model.addAttribute("pollen", service.getResultByRequest(cityName));
        model.addAttribute("viewDate", service.currentTime());

        return "index";
    }
}