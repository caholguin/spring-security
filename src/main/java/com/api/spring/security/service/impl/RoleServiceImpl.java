package com.api.spring.security.service.impl;

import com.api.spring.security.model.security.Role;
import com.api.spring.security.repository.security.RoleRepository;
import com.api.spring.security.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Optional<Role> findDefaultRole(){
        return roleRepository.findByName("CUSTOMER");
    }
}
