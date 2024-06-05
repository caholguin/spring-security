package com.api.spring.security.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.math.BigDecimal;

public class SaveProductDTO implements Serializable {

    @NotBlank(message = "El nombre no puede estar vacio")
    private String name;

    @DecimalMin(value="0.01", message = "El precio debe ser mayor a cero")
    private BigDecimal price;

    @Min(value=1)
    private Long categoryId;

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public BigDecimal getPrice(){
        return price;
    }

    public void setPrice(BigDecimal price){
        this.price = price;
    }

    public Long getCategoryId(){
        return categoryId;
    }

    public void setCategoryId(Long categoryId){
        this.categoryId = categoryId;
    }
}
