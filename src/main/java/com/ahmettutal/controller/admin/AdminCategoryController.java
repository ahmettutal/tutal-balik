package com.ahmettutal.controller.admin;

import com.ahmettutal.core.AdminController;
import com.ahmettutal.exception.InvalidCategoryException;
import com.ahmettutal.model.Category;
import com.ahmettutal.service.CategoryService;
import com.ahmettutal.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

import static com.ahmettutal.util.Constants.COMPANY_ID;
import static com.ahmettutal.util.Constants.UPLOAD_DIR_CATEGORY;

@AdminController
public class AdminCategoryController {

    @Autowired
    private CategoryService service;

    @Autowired
    private PictureService pictureService;

    @Autowired
    private HttpServletRequest request;


    @GetMapping("categories")
    String list(Model model) {

        model.addAttribute("categories", service.findAllByCompanyId(COMPANY_ID));

        return "admin/categories";
    }

    @GetMapping("categories/new")
    String newRecord(Model model) {

        model.addAttribute("category", new Category());
        List<Category> categories = service.findAllByCompanyId(COMPANY_ID);

        if (null != categories) {
            model.addAttribute("categories", categories.stream().filter(c -> c.getParent() == null).collect(Collectors.toList()));
        }

        return "admin/category";
    }

    @GetMapping("categories/delete/{id}")
    String delete(Model model, @PathVariable Long id) {

        service.delete(id);
        pictureService.deleteOfCategories(request, id);

        return "redirect:/admin/categories";
    }

    @GetMapping("categories/delete-image/{categoryId}/{pictureId}")
    String deleteImage(Model model, @PathVariable Long categoryId, @PathVariable Long pictureId) {

        pictureService.deletePic(request, null, categoryId, pictureId, UPLOAD_DIR_CATEGORY);

        return "redirect:/admin/categories/" + categoryId;
    }

    @GetMapping("categories/{id}")
    String edit(Model model, @PathVariable Long id) {

        model.addAttribute("category", service.findById(id));

        List<Category> categories = service.findAllByCompanyId(COMPANY_ID);

        if (null != categories) {
            model.addAttribute("categories", categories.stream().filter(c -> !c.getId().equals(id) && c.getParent() == null).collect(Collectors.toList()));
        }

        model.addAttribute("pictures", pictureService.findAllByCategoryId(id));

        return "admin/category";
    }

    @PostMapping("categories/save")
    String save(@ModelAttribute("category") Category category, @RequestParam("files") MultipartFile[] images) throws InvalidCategoryException {

        if (category.getParent() != null && category.getParent().getParent() != null) {
            throw new InvalidCategoryException("Üst Kategorisi olan bir kategoriyi Üst kategori seçemezsin");
        }

        if (null != category.getId()) {
            Category byId = service.findById(category.getId());

            byId.setName(category.getName());
            byId.setParent(category.getParent());

            service.save(byId, COMPANY_ID);
        } else {
            service.save(category, COMPANY_ID);
        }

        if (images != null) {
            pictureService.saveImages(request, null, category, images, UPLOAD_DIR_CATEGORY);
        }

        return "redirect:/admin/categories";
    }

}
