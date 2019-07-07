package com.ahmettutal.boundary;

import com.ahmettutal.core.APIRestController;
import com.ahmettutal.exception.ResourceNotFoundException;
import com.ahmettutal.model.Category;
import com.ahmettutal.repository.CategoryRepository;
import com.ahmettutal.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

@APIRestController
public class CategoryBoundary {

    @Autowired
    private CategoryRepository repository;


    @GetMapping("/category/{id}")
    public Category get(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Entity not found with id " + id));
    }

    @GetMapping("/categories")
    public List<Category> getAllCategories() {
        return repository.findAllByCompanyId(Constants.COMPANY_ID);
    }

    @PostMapping("/category")
    public Category create(@Valid @RequestBody Category product) {
        return repository.save(product);
    }

}
