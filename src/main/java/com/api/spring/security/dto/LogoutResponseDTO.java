package com.api.spring.security.dto;

import java.io.Serializable;

public class LogoutResponseDTO implements Serializable {

    private String message;

    public LogoutResponseDTO(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }

    public void setMessage(String message){
        this.message = message;
    }
}
