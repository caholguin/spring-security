package com.api.spring.security.service;

import com.api.spring.security.dto.SaveCategoryDTO;
import com.api.spring.security.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CategoryService {

    Page<Category> findAll(Pageable pageable);

    Optional<Category> findById(Long categoryId);

    Category create(SaveCategoryDTO saveCategoryDTO);

    Category update(Long categoryId, SaveCategoryDTO saveCategoryDTO);

    Category disabled(Long categoryId);
}
