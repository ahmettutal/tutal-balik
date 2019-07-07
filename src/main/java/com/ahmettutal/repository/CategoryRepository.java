package com.ahmettutal.repository;

import com.ahmettutal.model.Category;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends BaseRepository<Category, Long> {

    List<Category> findAllByCompanyId(Long companyId);

    Category findByLink(String link);

    Category findByParentAndLink(Category parent, String link);

    List<Category> findAllByCompanyIdOrderById(Long companyId);

    List<Category> findAllByParent(Category parent);

    List<Category> findAllByCompanyIdAndParent(Long companyId, Category parent);

}
