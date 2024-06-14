package com.api.spring.security.dto;

import com.api.spring.security.model.security.GrantedPermission;

import java.util.List;

public class RoleDTO {

    private Long id;
    private String name;
    List<GrantedPermissionDTO> grantedPermissions;
    List<OperationDTO> operations;

    public RoleDTO(){
    }

    public RoleDTO(Long id, String name, List<GrantedPermissionDTO> grantedPermissions, List<OperationDTO> operations){
        this.id = id;
        this.name = name;
        this.grantedPermissions = grantedPermissions;
        this.operations = operations;
    }

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public List<GrantedPermissionDTO> getGrantedPermissions(){
        return grantedPermissions;
    }

    public void setGrantedPermissions(List<GrantedPermissionDTO> grantedPermissions){
        this.grantedPermissions = grantedPermissions;
    }

    public List<OperationDTO> getOperations(){
        return operations;
    }

    public void setOperations(List<OperationDTO> operations){
        this.operations = operations;
    }
}
