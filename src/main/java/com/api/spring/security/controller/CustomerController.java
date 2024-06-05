package com.api.spring.security.controller;

import com.api.spring.security.dto.RegisteredUserDTO;
import com.api.spring.security.dto.SaveUserDTO;
import com.api.spring.security.service.auth.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<RegisteredUserDTO> register(@RequestBody @Valid SaveUserDTO saveUser){
        RegisteredUserDTO registerUser = authenticationService.registerCustomer(saveUser);
        return new ResponseEntity<>(registerUser, HttpStatus.CREATED);
    }
}
