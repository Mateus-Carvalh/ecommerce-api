package com.mateus.ecommerce.dto;



import java.math.BigDecimal;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class ProductRequest {

    @NotBlank(message = "O nome é obrigatório")
private String name;

@NotBlank(message = "A descrição é obrigatória")
private String description;

@Positive(message = "O preço deve ser maior que zero")
private BigDecimal price;

@Min(value = 0, message = "O estoque não pode ser negativo")
private Integer stock;

    public ProductRequest() {
    }

    public ProductRequest(
            String name,
            String description,
            BigDecimal price,
            Integer stock) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}