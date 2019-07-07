package com.ahmettutal.service;

import com.ahmettutal.exception.ResourceNotFoundException;
import com.ahmettutal.model.Category;
import com.ahmettutal.repository.CategoryRepository;
import com.ahmettutal.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Autowired
    private CompanyRepository companyRepository;


    public List<Category> findAllByCompanyId(Long companyId) {
        return repository.findAllByCompanyId(companyId);
    }

    public Category findByLink(String link) {
        return repository.findByLink(link);
    }

    public List<Category> findAllByCompanyIdOrderById(Long companyId) {
        return repository.findAllByCompanyIdOrderById(companyId);
    }

    public List<Category> findAllByParent(Category parent) {
        return repository.findAllByParent(parent);
    }

    public Category findByParentAndLink(Category parent, String link) {
        return repository.findByParentAndLink(parent, link);
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
