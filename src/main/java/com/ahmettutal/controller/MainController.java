package com.ahmettutal.controller;

import com.ahmettutal.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = {"", "/", "index.*", "home.*", "index", "home", "anasayfa"})
public class MainController {

    @Autowired
    private HomeService homeService;


    @GetMapping
    public String get(Model model) {

        homeService.prepareCategories(model);

        return "index";
    }

}
