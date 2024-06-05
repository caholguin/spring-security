package com.api.spring.security.dto;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

public class SaveCategoryDTO implements Serializable {

    @NotBlank
    private String name;

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }
}
