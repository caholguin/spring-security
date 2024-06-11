package com.api.spring.security.controller;

import com.api.spring.security.dto.SaveCategoryDTO;
import com.api.spring.security.model.Category;
import com.api.spring.security.service.CategoryService;
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
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PreAuthorize("hasAuthority('READ_ALL_CATEGORIES')")
    @GetMapping
    public ResponseEntity<Page<Category>> findAll(Pageable pageable) {

        Page<Category> categoriesPage = categoryService.findAll(pageable);

        if(categoriesPage.hasContent()) {
            return new ResponseEntity<>(categoriesPage,HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PreAuthorize("hasAuthority('READ_ONE_CATEGORY')")
    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> findById(@PathVariable Long categoryId) {
        Optional<Category> category = categoryService.findById(categoryId);

        if(category.isPresent()) {
            return new ResponseEntity<>(category.get(),HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PreAuthorize("hasAuthority('CREATE_ONE_CATEGORY')")
    @PostMapping()
    public ResponseEntity<Category> create(@RequestBody @Valid SaveCategoryDTO saveCategoryDTO) {
        Category category = categoryService.create(saveCategoryDTO);
        return new ResponseEntity<>(category,HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('UPDATE_ONE_CATEGORY')")
    @PutMapping("/{categoryId}")
    public ResponseEntity<Category> update(@PathVariable Long categoryId, @RequestBody @Valid SaveCategoryDTO saveCategoryDTO) {
        Category category = categoryService.update(categoryId, saveCategoryDTO);
        return new ResponseEntity<>(category,HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('DISABLED_ONE_CATEGORY')")
    @PutMapping("/{categoryId}/disabled")
    public ResponseEntity<Category> disabled(@PathVariable Long categoryId) {
        Category category = categoryService.disabled(categoryId);
        return new ResponseEntity<>(category,HttpStatus.OK);
    }
}
