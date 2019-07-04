package com.ahmettutal.repository;

import com.ahmettutal.model.ProductCategory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCategoryRepository extends BaseRepository<ProductCategory, Long> {

    List<ProductCategory> findAllByCompanyId(Long companyId);

    List<ProductCategory> findAllByParent(ProductCategory parent);

    List<ProductCategory> findAllByCompanyIdAndParent(Long companyId, ProductCategory parent);

}
