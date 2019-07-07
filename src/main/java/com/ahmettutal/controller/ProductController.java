package com.ahmettutal.controller;

import com.ahmettutal.model.Product;
import com.ahmettutal.service.HomeService;
import com.ahmettutal.service.PictureService;
import com.ahmettutal.service.ProductService;
import com.ahmettutal.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private HomeService homeService;

    @Autowired
    private ProductService service;

    @Autowired
    private PictureService pictureService;


    @GetMapping
    public String get(Model model) {

        homeService.prepareCategories(model);

        List<Product> products = service.findAllByCompanyId(Constants.COMPANY_ID);

        products.forEach(product -> product.setPicture(pictureService.findByProductId(product.getId())));

        model.addAttribute("products", products);

        return "products";
    }

}
