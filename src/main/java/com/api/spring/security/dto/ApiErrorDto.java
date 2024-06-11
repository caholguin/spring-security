package com.api.spring.security.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class ApiErrorDto implements Serializable {

    private String backendMessage;

    private String message;

    private String url;

    private String method;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;

    private List<Map<String, String>> validationErrors;  // Mapa para los errores de validación


    public String getBackendMessage(){
        return backendMessage;
    }

    public void setBackendMessage(String backendMessage){
        this.backendMessage = backendMessage;
    }

    public String getMessage(){
        return message;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public String getUrl(){
        return url;
    }

    public void setUrl(String url){
        this.url = url;
    }

    public String getMethod(){
        return method;
    }

    public void setMethod(String method){
        this.method = method;
    }

    public LocalDateTime getTimestamp(){
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp){
        this.timestamp = timestamp;
    }

    public List<Map<String, String>> getValidationErrors(){
        return validationErrors;
    }

    public void setValidationErrors(List<Map<String, String>> validationErrors){
        this.validationErrors = validationErrors;
    }
}
