package com.ahmettutal.boundary;

import com.ahmettutal.core.APIRestController;
import com.ahmettutal.exception.ResourceNotFoundException;
import com.ahmettutal.model.Product;
import com.ahmettutal.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

@APIRestController
public class ProductBoundary {

    @Autowired
    private ProductRepository repository;


    @GetMapping
    public Page<Product> get(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @GetMapping("/product/{id}")
    public Product get(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Entity not found with id " + id));
    }

    @PostMapping("/product")
    public Product create(@Valid @RequestBody Product product) {

        return repository.save(product);
    }

    @PutMapping("/product/{id}")
    public Product update(@PathVariable Long id, @Valid @RequestBody Product productRequest) {
        return repository.findById(id)
                .map(product -> {
                    product.setName(productRequest.getName());
                    product.setUpdatedAt(new Date());
                    return repository.save(product);
                }).orElseThrow(() -> new ResourceNotFoundException("Entity not found with id " + id));
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return repository.findById(id)
                .map(p -> {
                    repository.delete(p);
                    return ResponseEntity.ok().build();
                }).orElseThrow(() -> new ResourceNotFoundException("Entity not found with id " + id));
    }
}
