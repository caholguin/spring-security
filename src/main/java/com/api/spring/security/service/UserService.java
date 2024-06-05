package com.api.spring.security.service;

import com.api.spring.security.dto.SaveUserDTO;
import com.api.spring.security.model.User;

import java.util.Optional;

public interface UserService {
    User createCustomer(SaveUserDTO saveUser);

    Optional<User> findByUsername(String username);
}
