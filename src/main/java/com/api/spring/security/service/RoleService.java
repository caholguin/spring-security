package com.api.spring.security.service;

import com.api.spring.security.model.security.Role;

import java.util.Optional;

public interface RoleService {
   Optional<Role> findDefaultRole();
}
