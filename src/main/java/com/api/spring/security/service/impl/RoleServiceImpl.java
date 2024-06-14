package com.api.spring.security.service.impl;

import com.api.spring.security.dto.RoleDTO;
import com.api.spring.security.exception.ObjectNotFoundException;
import com.api.spring.security.mapper.OperationMapper;
import com.api.spring.security.mapper.RoleMapper;
import com.api.spring.security.model.security.GrantedPermission;
import com.api.spring.security.model.security.Operation;
import com.api.spring.security.model.security.Role;
import com.api.spring.security.repository.security.GrantedPermissionRepository;
import com.api.spring.security.repository.security.RoleRepository;
import com.api.spring.security.service.RoleService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private GrantedPermissionRepository grantedPermissionRepository;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private OperationMapper operationMapper;


    @Override
    public Optional<Role> findDefaultRole(){
        return roleRepository.findByName("CUSTOMER");
    }

    @Override
    public List<RoleDTO> getRoles() {
        List<Role> roles = roleRepository.findAll();
        return roles.stream()
                .map(roleMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<RoleDTO> findById(Long id){
        Optional<Role> role = roleRepository.findById(id);
        return role.map(roleMapper::toDTO);
    }

    @Override
    public RoleDTO save(RoleDTO roleDto){

        Role role = roleMapper.toEntity(roleDto);

        // Mapear las operaciones desde DTO a entidades
        List<Operation> operations = roleDto.getOperations().stream()
                .map(operationDTO -> operationMapper.toEntity(operationDTO))
                .collect(Collectors.toList());

        role = roleRepository.save(role);

        // Crear los objetos GrantedPermission y asociarlos al rol
        for (Operation operation : operations) {
            GrantedPermission grantedPermission = new GrantedPermission();
            grantedPermission.setRole(role);
            grantedPermission.setOperation(operation);

            grantedPermissionRepository.save(grantedPermission);
        }

        // Guardar el rol
        return roleMapper.toDTO(role);

    }

    @Override
    @Transactional
    public RoleDTO update(Long id,RoleDTO roleDto){
        //Role role = roleRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Role not faund: " + id));

        Optional<Role> roleOptional = roleRepository.findById(id);

        if (roleOptional.isEmpty()) {
            throw new ObjectNotFoundException("Category not found with id " + id);
        }

        Role role = roleOptional.get();
        role.setName(roleDto.getName());

        // Mapear las operaciones desde DTO a entidades
        List<Operation> operations = roleDto.getOperations().stream()
                .map(operationDTO -> operationMapper.toEntity(operationDTO))
                .collect(Collectors.toList());

        role = roleRepository.save(role);

        //borrar permisos
        grantedPermissionRepository.deleteByRole(role);

        // Crear los objetos GrantedPermission y asociarlos al rol
        for (Operation operation : operations) {
            GrantedPermission grantedPermission = new GrantedPermission();
            grantedPermission.setRole(role);
            grantedPermission.setOperation(operation);

            grantedPermissionRepository.save(grantedPermission);
        }


        return roleMapper.toDTO(role);
    }




}
