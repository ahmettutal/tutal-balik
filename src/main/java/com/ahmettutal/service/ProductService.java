package com.ahmettutal.service;

import com.ahmettutal.exception.ResourceNotFoundException;
import com.ahmettutal.model.Category;
import com.ahmettutal.model.Product;
import com.ahmettutal.repository.CompanyRepository;
import com.ahmettutal.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private CompanyRepository companyRepository;


    public List<Product> findAllByCompanyId(Long companyId) {
        return repository.findAllByCompanyId(companyId);
    }

    public List<Product> findAllByCompanyIdOrderById(Long companyId) {
        return repository.findAllByCompanyIdOrderById(companyId);
    }

    public List<Product> findAllByCategory(Category category) {
        return repository.findAllByCategory(category);
    }

    public Product findById(Long id) {

        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Entity not found with id " + id));
    }

    public Product save(Product product, Long companyId) {

        product.setCompany(companyRepository.getOne(companyId));

        return repository.save(product);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

}
