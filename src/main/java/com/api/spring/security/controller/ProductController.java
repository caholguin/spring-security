package com.api.spring.security.controller;

import com.api.spring.security.dto.SaveProductDTO;
import com.api.spring.security.model.Product;
import com.api.spring.security.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<Page<Product>> findAll(Pageable pageable) {

        Page<Product> productsPage = productService.findAll(pageable);

        if(productsPage.hasContent()) {
            return new ResponseEntity<>(productsPage,HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> findById(@PathVariable Long productId) {
        Optional<Product> product = productService.findById(productId);

        if(product.isPresent()) {
            return new ResponseEntity<>(product.get(),HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping()
    public ResponseEntity<Product> create(@RequestBody @Valid SaveProductDTO saveProductDTO) {
        Product product = productService.create(saveProductDTO);
        return new ResponseEntity<>(product,HttpStatus.CREATED);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<Product> update(@PathVariable Long productId, @RequestBody @Valid SaveProductDTO saveProductDTO) {
        Product product = productService.update(productId, saveProductDTO);
        return new ResponseEntity<>(product,HttpStatus.OK);
    }

    @PutMapping("/{productId}/disabled")
    public ResponseEntity<Product> disabled(@PathVariable Long productId) {
        Product product = productService.disabled(productId);
        return new ResponseEntity<>(product,HttpStatus.OK);
    }
}
