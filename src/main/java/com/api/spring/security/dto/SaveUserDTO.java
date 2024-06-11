package com.api.spring.security.dto;

import jakarta.validation.constraints.Size;

import java.io.Serializable;

public class SaveUserDTO implements Serializable {

    @Size(min = 4)
    private String name;
    @Size(min = 4)
    private String username;
    @Size(min = 5)
    private String password;
    @Size(min = 5)
    private String repeatPassword;

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getRepeatPassword(){
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword){
        this.repeatPassword = repeatPassword;
    }
}
