package com.api.spring.security.controller;

import com.api.spring.security.dto.RegisteredUserDTO;
import com.api.spring.security.dto.SaveUserDTO;
import com.api.spring.security.model.User;
import com.api.spring.security.service.auth.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private AuthenticationService authenticationService;

    @PreAuthorize("permitAll")
    @PostMapping
    public ResponseEntity<RegisteredUserDTO> register(@RequestBody @Valid SaveUserDTO saveUser){
        RegisteredUserDTO registerUser = authenticationService.registerCustomer(saveUser);
        return new ResponseEntity<>(registerUser, HttpStatus.CREATED);
    }

    @PreAuthorize("denyAll")
    @GetMapping
    public ResponseEntity<List<User>> findAll(){
        return new ResponseEntity<>(Arrays.asList(),HttpStatus.OK);
    }
}
