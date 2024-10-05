package com.miha.assignment1.db.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "taxation")
public class TaxationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private TaxationType taxationType;

    private Double taxRate;

    public TaxationEntity(TaxationType taxationType, Double taxRate) {
        this.taxationType = taxationType;
        this.taxRate = taxRate;
    }

    public TaxationEntity() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TaxationType getTaxationType() {
        return taxationType;
    }

    public void setTaxationType(TaxationType taxationType) {
        this.taxationType = taxationType;
    }

    public Double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
    }
}
