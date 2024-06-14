package com.api.spring.security.controller;

import com.api.spring.security.dto.OperationDTO;
import com.api.spring.security.dto.RoleDTO;
import com.api.spring.security.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/operations")
public class OperationController {
    
    @Autowired
    private OperationService operationService;


    @GetMapping
    public ResponseEntity<List<OperationDTO>> getRoles(){
        List<OperationDTO> operations = operationService.getOperations();
        return new ResponseEntity<>(operations, HttpStatus.OK);
    }
    
}
