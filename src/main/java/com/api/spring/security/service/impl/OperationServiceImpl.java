package com.api.spring.security.service.impl;

import com.api.spring.security.dto.OperationDTO;

import com.api.spring.security.mapper.OperationMapper;
import com.api.spring.security.model.security.Operation;
import com.api.spring.security.model.security.Role;
import com.api.spring.security.repository.security.OperationRepository;
import com.api.spring.security.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OperationServiceImpl implements OperationService {

    @Autowired
    private OperationRepository operationRepository;

    @Autowired
    private OperationMapper operationMapper;



    @Override
    public List<OperationDTO> getOperations(){
        List<Operation> roles = operationRepository.findAll();
        return roles.stream()
                .map(operationMapper::toDTO)
                .collect(Collectors.toList());
    }
}
