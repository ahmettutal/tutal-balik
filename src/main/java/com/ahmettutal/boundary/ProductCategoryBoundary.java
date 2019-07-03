package com.ahmettutal.boundary;

import com.ahmettutal.core.APIRestController;
import com.ahmettutal.exception.ResourceNotFoundException;
import com.ahmettutal.model.ProductCategory;
import com.ahmettutal.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@APIRestController
public class ProductCategoryBoundary {

    @Autowired
    private ProductCategoryRepository repository;


    @GetMapping("/product-category/{id}")
    public ProductCategory get(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Entity not found with id " + id));
    }

    @PostMapping("/product-category")
    public ProductCategory create(@Valid @RequestBody ProductCategory product) {
        return repository.save(product);
    }

}
