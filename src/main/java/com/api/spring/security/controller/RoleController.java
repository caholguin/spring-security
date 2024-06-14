package com.api.spring.security.controller;

import com.api.spring.security.dto.RoleDTO;
import com.api.spring.security.dto.SaveCategoryDTO;
import com.api.spring.security.exception.ObjectNotFoundException;
import com.api.spring.security.model.Category;
import com.api.spring.security.service.RoleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;


    @GetMapping
    public ResponseEntity<List<RoleDTO>> getRoles(){
        List<RoleDTO> roles = roleService.getRoles();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDTO> getRoleById(@PathVariable Long id) {
        RoleDTO role = roleService.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Role not found with id " + id));
        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RoleDTO> create(@RequestBody RoleDTO roleDto) {
        RoleDTO createMedico = roleService.save(roleDto);
        return new ResponseEntity<>(createMedico,HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoleDTO> update(@PathVariable Long id, @RequestBody @Valid RoleDTO roleDTO) {
        RoleDTO role = roleService.update(id,roleDTO);
        return new ResponseEntity<>(role,HttpStatus.OK);
    }

}
