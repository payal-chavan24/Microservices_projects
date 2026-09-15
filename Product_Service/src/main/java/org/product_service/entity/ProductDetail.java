package org.product_service.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "product_detail")
public class ProductDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "brand", length = 255)
    private String brand;

    @Column(name = "warranty", length = 255)
    private String warranty;

    public ProductDetail() {
    }

    public ProductDetail(String description, String brand, String warranty) {
        this.description = description;
        this.brand = brand;
        this.warranty = warranty;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getWarranty() {
        return warranty;
    }

    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }
}