package com.api.spring.security.service;

import com.api.spring.security.dto.RoleDTO;
import com.api.spring.security.model.security.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
   Optional<Role> findDefaultRole();

   List<RoleDTO> getRoles();

   Optional<RoleDTO> findById(Long id);

   RoleDTO save(RoleDTO role);

   RoleDTO update(Long id,RoleDTO roleDto);

   void delete(Long id);
}
