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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PreAuthorize("hasAuthority('READ_ALL_PRODUCTS')")
    @GetMapping
    public ResponseEntity<Page<Product>> findAll(Pageable pageable) {

        Page<Product> productsPage = productService.findAll(pageable);

        if(productsPage.hasContent()) {
            return new ResponseEntity<>(productsPage,HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PreAuthorize("hasAuthority('READ_ONE_PRODUCT')")
    @GetMapping("/{productId}")
    public ResponseEntity<Product> findById(@PathVariable Long productId) {
        Optional<Product> product = productService.findById(productId);

        if(product.isPresent()) {
            return new ResponseEntity<>(product.get(),HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PreAuthorize("hasAuthority('CREATE_ONE_PRODUCT')")
    @PostMapping()
    public ResponseEntity<Product> create(@RequestBody @Valid SaveProductDTO saveProductDTO) {
        Product product = productService.create(saveProductDTO);
        return new ResponseEntity<>(product,HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('UPDATE_ONE_PRODUCT')")
    @PutMapping("/{productId}")
    public ResponseEntity<Product> update(@PathVariable Long productId, @RequestBody @Valid SaveProductDTO saveProductDTO) {
        Product product = productService.update(productId, saveProductDTO);
        return new ResponseEntity<>(product,HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('DISABLED_ONE_PRODUCT')")
    @PutMapping("/{productId}/disabled")
    public ResponseEntity<Product> disabled(@PathVariable Long productId) {
        Product product = productService.disabled(productId);
        return new ResponseEntity<>(product,HttpStatus.OK);
    }
}
