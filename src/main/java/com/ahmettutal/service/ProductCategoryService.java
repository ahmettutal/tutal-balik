package com.ahmettutal.service;

import com.ahmettutal.exception.ResourceNotFoundException;
import com.ahmettutal.model.Category;
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


    public List<Category> findAllByCompanyId(Long companyId) {
        return repository.findAllByCompanyId(companyId);
    }

    public List<Category> findAllByParent(Category parent) {
        return repository.findAllByParent(parent);
    }

    public List<Category> findAllByCompanyIdAndParent(Long companyId, Category parent) {
        return repository.findAllByCompanyIdAndParent(companyId, parent);
    }

    public Category findById(Long id) {

        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Entity not found with id " + id));
    }

    public Category save(Category category, Long companyId) {

        category.setCompany(companyRepository.getOne(companyId));

        return repository.save(category);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}
