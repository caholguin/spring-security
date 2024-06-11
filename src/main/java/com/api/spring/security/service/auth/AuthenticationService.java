package com.api.spring.security.service.auth;

import com.api.spring.security.dto.RegisteredUserDTO;
import com.api.spring.security.dto.SaveUserDTO;
import com.api.spring.security.dto.auth.AutenticationRequestDTO;
import com.api.spring.security.dto.auth.AuthenticationResponseDTO;
import com.api.spring.security.exception.ObjectNotFoundException;
import com.api.spring.security.model.security.User;
import com.api.spring.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationService {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;


    public RegisteredUserDTO registerCustomer(SaveUserDTO saveUserDto){
        User user = userService.createCustomer(saveUserDto);

        RegisteredUserDTO userDTO = new RegisteredUserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setUsername(user.getUsername());
        userDTO.setRole(user.getRole().getName());

        String jwt = jwtService.generateToken(user, generateExtraClaims(user));
        userDTO.setJwt(jwt);

        return userDTO;
    }

    private Map<String, Object> generateExtraClaims(User user){
        Map<String, Object> extraClaims = new HashMap<>();

        extraClaims.put("name", user.getName());
        extraClaims.put("role", user.getRole().getName());
        extraClaims.put("authorities", user.getAuthorities());

        return extraClaims;
    }

    public AuthenticationResponseDTO login(AutenticationRequestDTO authRequestDTO){

        Authentication authentication = new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword());

        authenticationManager.authenticate(authentication);

        UserDetails user = userService.findByUsername(authRequestDTO.getUsername()).get();

        String jwt = jwtService.generateToken(user, generateExtraClaims((User) user));

        AuthenticationResponseDTO authenticationResponseDTO = new AuthenticationResponseDTO();

        authenticationResponseDTO.setJwt(jwt);

        return authenticationResponseDTO;

    }

    public boolean validateToken(String jwt){

        try {
            jwtService.extractUsername(jwt);
            return true;
        }catch (Exception exception){
            System.out.println(exception.getMessage());
            return false;
        }

    }

    public User findLoggedInUser(){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth instanceof  UsernamePasswordAuthenticationToken authToken) {
           String username = (String)authToken.getPrincipal();

           return userService.findByUsername(username).orElseThrow(() -> new ObjectNotFoundException("User not found. Username: " + username));
        }

        return null;

    }
}
