package com.ahmettutal.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Table(name = "PRODUCTS")
public class Product extends AuditModel {

    @Id
    @GeneratedValue(generator = "product_generator")
    @SequenceGenerator(name = "product_generator", sequenceName = "product_generator", initialValue = 1000)
    private Long id;

    @NotBlank
    @Size(min = 3, max = 100)
    private String name;

    @ManyToOne
    private Company company;

    @ManyToOne
    private Category category;

    private String description;

    private String brand;

    private Integer quantity;

    private BigDecimal price;


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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
