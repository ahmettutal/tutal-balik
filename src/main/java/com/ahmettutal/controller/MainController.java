package com.ahmettutal.controller;

import com.ahmettutal.model.Category;
import com.ahmettutal.model.Picture;
import com.ahmettutal.service.PictureService;
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

    @Autowired
    private PictureService pictureService;

    private static final Long COMPANY = 1000L;


    @GetMapping
    public String get(Model model) {

        List<Category> mainCategories = categoryService.findAllByCompanyIdAndParent(COMPANY, null);

        Map<Long, List<Category>> subCategories = mainCategories.stream().collect(Collectors.toMap(Category::getId, mainCategory -> categoryService.findAllByParent(mainCategory), (a, b) -> b));

        model.addAttribute("leftCategories", mainCategories);
        model.addAttribute("subCategories", subCategories);

        Map<Long, Picture> mainCategoryPictures = new HashMap<>();

        mainCategories.forEach(category -> {
            Picture picture = pictureService.findByCategoryId(category.getId());
            if (picture != null) {
                mainCategoryPictures.put(category.getId(), picture);
            }
        });

        model.addAttribute("mainCategoryPictures", mainCategoryPictures);

        return "index";
    }

}
