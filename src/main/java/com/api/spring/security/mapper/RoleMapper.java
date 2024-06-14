package com.api.spring.security.mapper;

import com.api.spring.security.dto.GrantedPermissionDTO;
import com.api.spring.security.dto.OperationDTO;
import com.api.spring.security.dto.RoleDTO;
import com.api.spring.security.model.security.GrantedPermission;
import com.api.spring.security.model.security.Operation;
import com.api.spring.security.model.security.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RoleMapper {

    @Autowired
    private OperationMapper operationMapper;

    public RoleDTO toDTO(Role role) {
        RoleDTO roleDTO = new RoleDTO();

        roleDTO.setId(role.getId());
        roleDTO.setName(role.getName());

        List<OperationDTO> operationDTOs;

        if (role.getPermissions() != null) {
            operationDTOs = role.getPermissions().stream()
                    .map(GrantedPermission::getOperation)
                    .map(operationMapper::toDTO)
                    .collect(Collectors.toList());
        } else {
            operationDTOs = new ArrayList<>(); // Otra acción en caso de que role.getPermissions() sea null
        }

        roleDTO.setOperations(operationDTOs);

        return roleDTO;
    }

    public Role toEntity(RoleDTO roleDTO) {
        Role role = new Role();

        role.setId(roleDTO.getId());
        role.setName(roleDTO.getName());

       /* List<GrantedPermission> grantedPermissions = roleDTO.getGrantedPermissions().stream()
                .map(grantedPermissionDTO -> {
                    GrantedPermission grantedPermission = new GrantedPermission();
                    grantedPermission.setId(grantedPermissionDTO.getId());

                    List<Operation> operations = grantedPermissionDTO.getOperations().stream()
                            .map(operationDTO -> {
                                Operation operation = new Operation();
                                operation.setId(operationDTO.getId());
                                return operation;
                            })
                            .collect(Collectors.toList());

                    grantedPermission.setOperation((Operation) operations);
                    return grantedPermission;
                })
                .collect(Collectors.toList());

        role.setPermissions(grantedPermissions);*/

        return role;
    }
}
