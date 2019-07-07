package com.ahmettutal.repository;

import com.ahmettutal.model.Category;
import com.ahmettutal.model.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends BaseRepository<Product, Long> {

    List<Product> findAllByCompanyId(Long companyId);

    List<Product> findAllByCompanyIdOrderById(Long companyId);

    List<Product> findAllByCategory(Category category);

}
