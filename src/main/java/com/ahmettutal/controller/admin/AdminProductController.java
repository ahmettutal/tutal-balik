package com.ahmettutal.controller.admin;

import com.ahmettutal.core.AdminController;
import com.ahmettutal.model.Product;
import com.ahmettutal.service.CategoryService;
import com.ahmettutal.service.PictureService;
import com.ahmettutal.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

import static com.ahmettutal.util.Constants.COMPANY_ID;
import static com.ahmettutal.util.Constants.UPLOAD_DIR_PRODUCT;

@AdminController
public class AdminProductController {

    @Autowired
    private ProductService service;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private PictureService pictureService;


    @Autowired
    private HttpServletRequest request;

    @GetMapping("products")
    String list(Model model) {

        model.addAttribute("products", service.findAllByCompanyId(COMPANY_ID));

        return "admin/products";
    }

    @GetMapping("products/delete/{id}")
    String delete(@PathVariable Long id) {

        service.delete(id);
        pictureService.deleteOfProducts(request, id);

        return "redirect:/admin/categories";
    }

    @GetMapping("products/new")
    String newRecord(Model model) {

        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.findAllByCompanyId(COMPANY_ID));

        return "admin/product";
    }

    @GetMapping("products/{id}")
    String edit(Model model, @PathVariable Long id) {

        model.addAttribute("product", service.findById(id));
        model.addAttribute("pictures", pictureService.findAllByProductId(id));
        model.addAttribute("categories", categoryService.findAllByCompanyId(COMPANY_ID));

        return "admin/product";
    }

    @GetMapping("products/delete-image/{productId}/{pictureId}")
    String deleteImage(Model model, @PathVariable Long productId, @PathVariable Long pictureId) {

        pictureService.deletePic(request, productId, null, pictureId, UPLOAD_DIR_PRODUCT);

        return "redirect:/admin/products/" + productId;
    }

    @PostMapping("products/save")
    String save(@ModelAttribute("product") Product product, @RequestParam("files") MultipartFile[] images) {

        if (null != product.getId()) {
            Product byId = service.findById(product.getId());

            byId.setName(product.getName());
            byId.setDescription(product.getDescription());
            byId.setBrand(product.getBrand());
            byId.setQuantity(product.getQuantity());
            byId.setPrice(product.getPrice());
            byId.setCategory(product.getCategory());

            product = service.save(byId, COMPANY_ID);
        } else {
            product = service.save(product, COMPANY_ID);
        }

        if (images != null) {
            pictureService.saveImages(request, product, null, images, UPLOAD_DIR_PRODUCT);
        }

        return "redirect:/admin/products";
    }

}
