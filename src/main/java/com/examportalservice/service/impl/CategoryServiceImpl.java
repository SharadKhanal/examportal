package com.examportalservice.service.impl;

import com.examportalservice.entity.exam.Category;
import com.examportalservice.repo.CategoryRepository;
import com.examportalservice.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {
    private  final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category addCategory(Category category) {
        return this.categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Category category) {
        return this.categoryRepository.save(category);
    }

    @Override
    public Category getCategoryById(Long id) {
        return this.categoryRepository.findById(id).get();
    }

    @Override
    public Set<Category> getCategories() {
        return new LinkedHashSet<>(this.categoryRepository.findAll());
    }

    @Override
    public void deleteCategory(Long id) {
        Category category = new Category();
        category.setCid(id);
        this.categoryRepository.delete(category);
    }
}
