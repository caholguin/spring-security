package com.api.spring.security.service.impl;

import com.api.spring.security.dto.SaveCategoryDTO;
import com.api.spring.security.exception.ObjectNotFoundException;
import com.api.spring.security.model.Category;
import com.api.spring.security.repository.CategoryRepository;
import com.api.spring.security.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Page<Category> findAll(Pageable pageable){
        return categoryRepository.findAll(pageable);
    }

    @Override
    public Optional<Category> findById(Long categoId){
        return categoryRepository.findById(categoId);
    }

    @Override
    public Category create(SaveCategoryDTO saveCategoryDTO){

        Category category = new Category();

        category.setName(saveCategoryDTO.getName());

        return categoryRepository.save(category);
    }

    @Override
    public Category update(Long categoryId, SaveCategoryDTO saveCategoryDTO){
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ObjectNotFoundException("Category not found with id " + categoryId));

        category.setName(saveCategoryDTO.getName());

        return categoryRepository.save(category);
    }

    @Override
    public Category disabled(Long categoryId){
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ObjectNotFoundException("Category not found with id " + categoryId));

        category.setStatus(Category.CategoryStatus.DISABLED);

        return categoryRepository.save(category);
    }

}
