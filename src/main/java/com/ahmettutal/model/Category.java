package com.ahmettutal.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "PRODUCT_CATEGORY")
public class Category extends AuditModel {

    @Id
    @GeneratedValue(generator = "product_category_generator")
    @SequenceGenerator(name = "product_category_generator", sequenceName = "product_category_generator", initialValue = 1200)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String link;

    @ManyToOne
    private Company company;

    @OneToOne
    private Category parent;

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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }
}
