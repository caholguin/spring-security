package com.api.spring.security.service.impl;

import com.api.spring.security.dto.SaveProductDTO;
import com.api.spring.security.exception.ObjectNotFoundException;
import com.api.spring.security.model.Category;
import com.api.spring.security.model.Product;
import com.api.spring.security.repository.ProductRepository;
import com.api.spring.security.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Page<Product> findAll(Pageable pageable){
        return productRepository.findAll(pageable);
    }

    @Override
    public Optional<Product> findById(Long productId){
        return productRepository.findById(productId);
    }

    @Override
    public Product create(SaveProductDTO saveProductDTO){

        Product product = new Product();

        product.setName(saveProductDTO.getName());
        product.setPrice(saveProductDTO.getPrice());
        product.setStatus(Product.ProductStatus.ENABLED);

        Category category = new Category();
        category.setId(saveProductDTO.getCategoryId());
        product.setCategory(category);

        return productRepository.save(product);
    }

    @Override
    public Product update(Long productId, SaveProductDTO saveProductDTO){
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ObjectNotFoundException("Product not found with id " + productId));

        product.setName(saveProductDTO.getName());
        product.setPrice(saveProductDTO.getPrice());

        Category category = new Category();
        category.setId(saveProductDTO.getCategoryId());
        product.setCategory(category);

        return productRepository.save(product);
    }

    @Override
    public Product disabled(Long productId){
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ObjectNotFoundException("Product not found with id " + productId));

        product.setStatus(Product.ProductStatus.DISABLED);

        return productRepository.save(product);
    }
}
