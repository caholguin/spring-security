package com.api.spring.security.controller;

import com.api.spring.security.dto.auth.AutenticationRequestDTO;
import com.api.spring.security.dto.auth.AuthenticationResponseDTO;
import com.api.spring.security.model.User;
import com.api.spring.security.service.auth.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponseDTO> authenticate(@RequestBody @Valid AutenticationRequestDTO autenticationRequestDTO){

        AuthenticationResponseDTO res = authenticationService.login(autenticationRequestDTO);

        return new ResponseEntity<>(res, HttpStatus.OK);

    }

    @GetMapping("/validate-token")
    public ResponseEntity<Boolean> validate(@RequestParam String jwt){

        boolean isTokenValid = authenticationService.validateToken(jwt);
        return new ResponseEntity<>(isTokenValid, HttpStatus.OK);
    }

    @GetMapping("/profile")
    public ResponseEntity<User> profile(){
        User user = authenticationService.findLoggedInUser();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
