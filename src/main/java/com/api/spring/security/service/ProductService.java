package com.api.spring.security.service;

import com.api.spring.security.dto.SaveProductDTO;
import com.api.spring.security.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProductService {

    Page<Product> findAll(Pageable pageable);

    Optional<Product> findById(Long productId);

    Product create(SaveProductDTO saveProductDTO);

    Product update(Long productId, SaveProductDTO saveProductDTO);

    Product disabled(Long productId);
}
