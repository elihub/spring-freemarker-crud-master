/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.gruchala.crud.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author elida
 */
@Entity
@Table(name = "PRODUCTS")
public class Product {

    private static final long serialVersionUID = -172412219240754562L;
    private Long id;
    @NotEmpty(message = "Description cannot be empty")
    @Length(min = 3, max = 100, message = "Descripcion needs to have at least 5 characters")
    private String description;
    @NotEmpty(message = "Code cannot be empty")
    private String code;
    @NotEmpty(message = "Price cannot be empty")
    private Double price;
    @NotEmpty(message = "Amount cannot be empty")
    private Integer amount;
    private String hash;

    public Product() {
    }

    public Product(String description, String code, Double price, Integer amount, String hash) {
        this.description = description;
        this.code = code;
        this.price = price;
        this.amount = amount;
        this.hash = hash;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Product{");
        sb.append("id=").append(id);
        sb.append(", description='").append(description).append('\'');
        sb.append(", code=").append(code);
        sb.append(", price='").append(price).append('\'');
        sb.append(", amount='").append(amount).append('\'');
        sb.append(", hash='").append(hash).append('\'');
        sb.append('}');
        return sb.toString();
    }

}
