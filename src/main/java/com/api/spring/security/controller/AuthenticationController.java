package com.api.spring.security.controller;

import com.api.spring.security.dto.LogoutResponseDTO;
import com.api.spring.security.dto.auth.AutenticationRequestDTO;
import com.api.spring.security.dto.auth.AuthenticationResponseDTO;
import com.api.spring.security.model.security.User;
import com.api.spring.security.service.auth.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PreAuthorize("permitAll")
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponseDTO> authenticate(@RequestBody @Valid AutenticationRequestDTO autenticationRequestDTO){

        AuthenticationResponseDTO res = authenticationService.login(autenticationRequestDTO);

        return new ResponseEntity<>(res, HttpStatus.OK);

    }

    @PreAuthorize("permitAll")
    @GetMapping("/validate-token")
    public ResponseEntity<Boolean> validate(@RequestParam String jwt){

        boolean isTokenValid = authenticationService.validateToken(jwt);
        return new ResponseEntity<>(isTokenValid, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('READ_MY_PROFILE')")
    @GetMapping("/profile")
    public ResponseEntity<User> profile(){
        User user = authenticationService.findLoggedInUser();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<LogoutResponseDTO> logout(HttpServletRequest request){

        authenticationService.logout(request);
        return new ResponseEntity<>(new LogoutResponseDTO("logout exitoso"),HttpStatus.OK);

    }

}
