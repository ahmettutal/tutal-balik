package com.ahmettutal.boundary;

import com.ahmettutal.core.APIRestController;
import com.ahmettutal.exception.ResourceNotFoundException;
import com.ahmettutal.model.Company;
import com.ahmettutal.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@APIRestController
public class CompanyBoundary {

    @Autowired
    private CompanyRepository repository;


    @GetMapping("/company/{id}")
    public Company get(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Entity not found with id " + id));
    }

    @PostMapping("/company")
    public Company create(@Valid @RequestBody Company company) {

        return repository.save(company);
    }
}
