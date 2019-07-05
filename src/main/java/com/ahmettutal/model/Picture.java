package com.ahmettutal.model;

import javax.persistence.*;

@Entity
@Table(name = "PICTURES")
public class Picture extends AuditModel {

    @Id
    @GeneratedValue(generator = "picture_generator")
    @SequenceGenerator(name = "picture_generator", sequenceName = "picture_generator", initialValue = 1000)
    private Long id;

    private String name;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Category category;

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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
