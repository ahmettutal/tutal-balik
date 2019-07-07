package com.ahmettutal.service;

import com.ahmettutal.model.Category;
import com.ahmettutal.model.Picture;
import com.ahmettutal.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class HomeService {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private PictureService pictureService;


    public void prepareCategories(Model model) {

        List<Category> mainCategories = categoryService.findAllByCompanyIdAndParent(Constants.COMPANY_ID, null);

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
    }

}
