package com.tutal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = {"", "/", "index.*", "home.*", "index", "home", "anasayfa"})
public class HomeController {

    @GetMapping
    public String get(Model model) {
        return "index";
    }

}
