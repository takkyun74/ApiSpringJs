package com.example.springbootapi.controller;

import com.example.springbootapi.service.ViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class TopController {

    @Autowired
    private ViewService service;

    @RequestMapping("/top/")
    public String top(Model model, @RequestParam(name = "view", required = false) String view) {
        return service.getSceneName(view);
    }
}