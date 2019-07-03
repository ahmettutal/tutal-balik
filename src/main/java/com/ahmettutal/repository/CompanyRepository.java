package com.ahmettutal.repository;

import com.ahmettutal.model.Company;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends BaseRepository<Company, Long> {
}
