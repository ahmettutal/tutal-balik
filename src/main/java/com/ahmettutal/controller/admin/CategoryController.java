package com.ahmettutal.controller.admin;

import com.ahmettutal.model.ProductCategory;
import com.ahmettutal.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("categories")
public class CategoryController {

    @Autowired
    private ProductCategoryService service;

    private static final Long COMPANY = 1000L;

    @GetMapping
    String list(Model model) {

        model.addAttribute("categories", service.findAllByCompanyId(COMPANY));

        return "admin/categories";
    }

    @GetMapping("new")
    String newRecord(Model model) {

        model.addAttribute("category", new ProductCategory());
        model.addAttribute("categories", service.findAllByCompanyId(COMPANY));

        return "admin/category";
    }

    @GetMapping("{id}")
    String edit(Model model, @PathVariable Long id) {

        model.addAttribute("category", service.findById(id));

        List<ProductCategory> categories = service.findAllByCompanyId(COMPANY);

        if (null != categories) {
            model.addAttribute("categories", categories.stream().filter(c -> !c.getId().equals(id)).collect(Collectors.toList()));
        }

        return "admin/category";
    }

    @PostMapping("save")
    String save(@ModelAttribute("category") ProductCategory category) {

        if (null != category.getId()) {
            ProductCategory byId = service.findById(category.getId());

            byId.setName(category.getName());
            byId.setParent(category.getParent());

            service.save(byId, COMPANY);
        } else {
            service.save(category, COMPANY);
        }

        return "redirect:/categories";
    }

}
