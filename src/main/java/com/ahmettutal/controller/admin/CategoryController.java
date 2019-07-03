package com.ahmettutal.controller.admin;

import com.ahmettutal.core.AdminController;
import com.ahmettutal.exception.InvalidCategoryException;
import com.ahmettutal.model.ProductCategory;
import com.ahmettutal.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.stream.Collectors;

@AdminController
public class CategoryController {

    @Autowired
    private ProductCategoryService service;

    private static final Long COMPANY = 1000L;

    @GetMapping("categories")
    String list(Model model) {

        model.addAttribute("categories", service.findAllByCompanyId(COMPANY));

        return "admin/categories";
    }

    @GetMapping("categories/new")
    String newRecord(Model model) {

        model.addAttribute("category", new ProductCategory());
        model.addAttribute("categories", service.findAllByCompanyId(COMPANY));

        return "admin/category";
    }

    @GetMapping("categories/{id}")
    String edit(Model model, @PathVariable Long id) {

        model.addAttribute("category", service.findById(id));

        List<ProductCategory> categories = service.findAllByCompanyId(COMPANY);

        if (null != categories) {
            model.addAttribute("categories", categories.stream().filter(c -> !c.getId().equals(id)).collect(Collectors.toList()));
        }

        return "admin/category";
    }

    @PostMapping("categories/save")
    String save(@ModelAttribute("category") ProductCategory category) throws InvalidCategoryException {

        if (category.getParent() != null && category.getParent().getParent() != null) {
            throw new InvalidCategoryException("Üst Kategorisi olan bir kategoriyi Üst kategori seçemezsin");
        }

        if (null != category.getId()) {
            ProductCategory byId = service.findById(category.getId());

            byId.setName(category.getName());
            byId.setParent(category.getParent());

            service.save(byId, COMPANY);
        } else {
            service.save(category, COMPANY);
        }

        return "redirect:/admin/categories";
    }

}
