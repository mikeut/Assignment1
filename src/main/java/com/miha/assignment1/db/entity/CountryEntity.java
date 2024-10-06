package com.miha.assignment1.db.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "country")
public class CountryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String code;

    @ManyToOne
    @JoinColumn(name = "taxation_id")
    private TaxationEntity taxation;

    public CountryEntity() {
    }

    public CountryEntity(String name, String code, TaxationEntity taxation) {
        this.name = name;
        this.code = code;
        this.taxation = taxation;
    }

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public TaxationEntity getTaxation() {
        return taxation;
    }

    public void setTaxation(TaxationEntity taxation) {
        this.taxation = taxation;
    }
}
