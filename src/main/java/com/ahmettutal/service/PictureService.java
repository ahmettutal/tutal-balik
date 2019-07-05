package com.ahmettutal.service;

import com.ahmettutal.model.Category;
import com.ahmettutal.model.Picture;
import com.ahmettutal.model.Product;
import com.ahmettutal.repository.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

@Service
public class PictureService {

    @Autowired
    private PictureRepository repository;

    public void saveImages(HttpServletRequest request, Product product, Category category, MultipartFile[] images, String uploadDir) {

        for (MultipartFile image : images) {

            if (image.isEmpty()) continue;

            try {

                String fileName = StringUtils.cleanPath(image.getOriginalFilename());

                String realPathtoUploads = request.getServletContext().getRealPath(uploadDir);
                if (!new File(realPathtoUploads).exists()) {
                    new File(realPathtoUploads).mkdir();
                }

                if (product != null) {
                    realPathtoUploads += File.separator + product.getId();
                } else if (category != null) {
                    realPathtoUploads += File.separator + category.getId();
                } else {
                    return;
                }

                if (!new File(realPathtoUploads).exists()) {
                    new File(realPathtoUploads).mkdir();
                }

                String filePath = realPathtoUploads + File.separator + fileName;
                File dest = new File(filePath);
                image.transferTo(dest);

                Picture picture = new Picture();
                picture.setProduct(product);
                picture.setCategory(category);
                picture.setName(fileName);

                repository.save(picture);

            } catch (Exception e) {
                System.out.printf("saveImages e:" + e.getMessage(), e);
            }
        }

    }

    public void deletePic(HttpServletRequest request, Long productId, Long categoryId, Long pictureId, String uploadDir) {
        try {
            Picture picture = repository.findById(pictureId).orElse(null);
            repository.deleteById(pictureId);

            String uploadFolder = request.getServletContext().getRealPath(uploadDir);

            String fileName;

            if (productId != null) {
                fileName = uploadFolder + File.separator + productId + File.separator + picture.getName();
            } else if (categoryId != null) {
                fileName = uploadFolder + File.separator + categoryId + File.separator + picture.getName();
            } else {
                return;
            }

            File file = new File(fileName);
            file.delete();

        } catch (Exception e) {
            System.out.printf("deletePic e:" + e.getMessage(), e);
        }
    }

    public List<Picture> findAllByProductId(Long productId) {
        return repository.findAllByProductId(productId);
    }

    public List<Picture> findAllByCategoryId(Long categoryId) {
        return repository.findAllByCategoryId(categoryId);
    }

    public Picture findByCategoryId(Long categoryId) {
        return repository.findByCategoryId(categoryId);
    }

}
