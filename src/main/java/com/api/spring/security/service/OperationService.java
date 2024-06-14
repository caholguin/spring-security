package com.api.spring.security.service;

import com.api.spring.security.dto.OperationDTO;
import com.api.spring.security.dto.RoleDTO;
import com.api.spring.security.model.security.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OperationService {

    List<OperationDTO> getOperations();

}
