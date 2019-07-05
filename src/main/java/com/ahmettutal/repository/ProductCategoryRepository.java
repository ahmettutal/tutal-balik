package com.ahmettutal.repository;

import com.ahmettutal.model.Category;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCategoryRepository extends BaseRepository<Category, Long> {

    List<Category> findAllByCompanyId(Long companyId);

    List<Category> findAllByParent(Category parent);

    List<Category> findAllByCompanyIdAndParent(Long companyId, Category parent);

}
