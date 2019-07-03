package com.ahmettutal.controller;

import com.ahmettutal.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = {"", "/", "index.*", "home.*", "index", "home", "anasayfa"})
public class HomeController {

    @Autowired
    private ProductCategoryService categoryService;

    private static final Long COMPANY = 1000L;


    @GetMapping
    public String get(Model model) {

        model.addAttribute("leftCategories", categoryService.findAllByCompanyId(COMPANY));

        return "index";
    }

}
