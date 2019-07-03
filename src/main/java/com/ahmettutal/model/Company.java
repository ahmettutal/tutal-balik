package com.ahmettutal.model;

import javax.persistence.*;

@Entity
@Table(name = "COMPANY")
public class Company extends AuditModel {

    @Id
    @GeneratedValue(generator = "company_generator")
    @SequenceGenerator(name = "company_generator", sequenceName = "company_generator", initialValue = 1000)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
