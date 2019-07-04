package com.ahmettutal.controller;

import com.ahmettutal.model.ProductCategory;
import com.ahmettutal.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = {"", "/", "index.*", "home.*", "index", "home", "anasayfa"})
public class MainController {

    @Autowired
    private ProductCategoryService categoryService;

    private static final Long COMPANY = 1000L;


    @GetMapping
    public String get(Model model) {

        List<ProductCategory> mainCategories = categoryService.findAllByCompanyIdAndParent(COMPANY, null);

        Map<Long, List<ProductCategory>> subCategories = mainCategories.stream().collect(Collectors.toMap(ProductCategory::getId, mainCategory -> categoryService.findAllByParent(mainCategory), (a, b) -> b));

        model.addAttribute("leftCategories", mainCategories);
        model.addAttribute("subCategories", subCategories);

        return "index";
    }

}
