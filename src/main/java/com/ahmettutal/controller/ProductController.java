package com.ahmettutal.controller;

import com.ahmettutal.model.Category;
import com.ahmettutal.model.Product;
import com.ahmettutal.service.CategoryService;
import com.ahmettutal.service.HomeService;
import com.ahmettutal.service.PictureService;
import com.ahmettutal.service.ProductService;
import com.ahmettutal.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private HomeService homeService;

    @Autowired
    private ProductService service;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private PictureService pictureService;


    @GetMapping("/urunler")
    public String get(Model model) {

        homeService.prepareCategories(model);

        List<Product> products = service.findAllByCompanyId(Constants.COMPANY_ID);

        products.forEach(product -> product.setPicture(pictureService.findByProductId(product.getId())));

        model.addAttribute("products", products);

        return "products";
    }

    @GetMapping("/urunler/{categoryLink}/{subCategoryLink}")
    public String categoryList(Model model, @PathVariable String categoryLink, @PathVariable String subCategoryLink) {

        homeService.prepareCategories(model);

        Category category = categoryService.findByLink("/" + categoryLink);
        Category subCategory = categoryService.findByParentAndLink(category, "/" + subCategoryLink);

        List<Product> products = service.findAllByCategory(subCategory);

        products.forEach(product -> product.setPicture(pictureService.findByProductId(product.getId())));

        model.addAttribute("products", products);

        return "products";
    }

}
