package com.ahmettutal.service;

import com.ahmettutal.exception.ResourceNotFoundException;
import com.ahmettutal.model.ProductCategory;
import com.ahmettutal.repository.CompanyRepository;
import com.ahmettutal.repository.ProductCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductCategoryService {

    @Autowired
    private ProductCategoryRepository repository;

    @Autowired
    private CompanyRepository companyRepository;


    public List<ProductCategory> findAllByCompanyId(Long companyId) {
        return repository.findAllByCompanyId(companyId);
    }

    public ProductCategory findById(Long id) {

        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Entity not found with id " + id));
    }

    public ProductCategory save(ProductCategory category, Long companyId) {

        category.setCompany(companyRepository.getOne(companyId));

        return repository.save(category);
    }
}
