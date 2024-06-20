package com.api.spring.security.dto.auth;

import java.io.Serializable;

public class RefreshTokenDTO implements Serializable {

    private String token;

    public String getToken(){
        return token;
    }

    public void setToken(String token){
        this.token = token;
    }

    @Override
    public String toString(){
        return "RefreshTokenDTO{" +
                "token='" + token + '\'' +
                '}';
    }
}
