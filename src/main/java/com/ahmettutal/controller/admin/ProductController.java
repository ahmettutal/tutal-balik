package com.ahmettutal.controller.admin;

import com.ahmettutal.core.AdminController;
import com.ahmettutal.model.Picture;
import com.ahmettutal.model.Product;
import com.ahmettutal.repository.PictureRepository;
import com.ahmettutal.service.ProductCategoryService;
import com.ahmettutal.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@Controller
@AdminController
public class ProductController {

    @Autowired
    private ProductService service;

    @Autowired
    private ProductCategoryService categoryService;

    @Autowired
    private PictureRepository pictureRepository;


    @Autowired
    private HttpServletRequest request;

    public static final String UPLOAD_DIR = "/uploads/";
    private static final Long COMPANY = 1000L;

    @GetMapping("products")
    String list(Model model) {

        model.addAttribute("products", service.findAllByCompanyId(COMPANY));

        return "admin/products";
    }

    @GetMapping("products/new")
    String newRecord(Model model) {

        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.findAllByCompanyId(COMPANY));

        return "admin/product";
    }

    @GetMapping("{id}")
    String edit(Model model, @PathVariable Long id) {

        model.addAttribute("product", service.findById(id));
        model.addAttribute("pictures", pictureRepository.findAllByProductId(id));
        model.addAttribute("categories", categoryService.findAllByCompanyId(COMPANY));

        return "admin/product";
    }

    @GetMapping("products/delete-image/{productId}/{pictureId}")
    String deleteImage(Model model, @PathVariable Long productId, @PathVariable Long pictureId) {

        try {
            Picture picture = pictureRepository.findById(pictureId).orElse(null);
            pictureRepository.deleteById(pictureId);

            String uploadFolder = request.getServletContext().getRealPath(UPLOAD_DIR);

            File file = new File(uploadFolder + "/" + productId + "/" + picture.getName());
            file.delete();

        } catch (Exception e) {
        }

        model.addAttribute("product", service.findById(productId));
        model.addAttribute("pictures", pictureRepository.findAllByProductId(productId));
        model.addAttribute("categories", categoryService.findAllByCompanyId(COMPANY));

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

            product = service.save(byId, COMPANY);
        } else {
            product = service.save(product, COMPANY);
        }

        saveImages(product, images);

        return "redirect:/admin/products";
    }

    private void saveImages(Product product, MultipartFile[] images) {

        for (MultipartFile image : images) {

            if (image.isEmpty()) continue;

            try {

                String fileName = StringUtils.cleanPath(image.getOriginalFilename());

                String realPathtoUploads = request.getServletContext().getRealPath(UPLOAD_DIR);
                if (!new File(realPathtoUploads).exists()) {
                    new File(realPathtoUploads).mkdir();
                }
                realPathtoUploads += "/" + product.getId();
                if (!new File(realPathtoUploads).exists()) {
                    new File(realPathtoUploads).mkdir();
                }

                String filePath = realPathtoUploads + "/" + fileName;
                File dest = new File(filePath);
                image.transferTo(dest);

                Picture picture = new Picture();
                picture.setProduct(product);
                picture.setName(fileName);

                pictureRepository.save(picture);

            } catch (Exception e) {
                System.out.printf(e.getMessage(), e);
            }
        }

    }

}
