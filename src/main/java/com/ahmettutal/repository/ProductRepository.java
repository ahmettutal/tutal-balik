package com.ahmettutal.repository;

import com.ahmettutal.model.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends BaseRepository<Product, Long> {

    List<Product> findAllByCompanyId(Long companyId);

}
