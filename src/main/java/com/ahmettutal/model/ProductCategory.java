package com.ahmettutal.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "PRODUCT_CATEGORY")
public class ProductCategory extends AuditModel {

    @Id
    @GeneratedValue(generator = "product_category_generator")
    @SequenceGenerator(name = "product_category_generator", sequenceName = "product_category_generator", initialValue = 1000)
    private Long id;

    @NotBlank
    private String name;

    @ManyToOne
    private Company company;

    @OneToOne
    private ProductCategory parent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public ProductCategory getParent() {
        return parent;
    }

    public void setParent(ProductCategory parent) {
        this.parent = parent;
    }
}
