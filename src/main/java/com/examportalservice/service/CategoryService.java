package com.examportalservice.service;

import com.examportalservice.entity.exam.Category;

import java.util.List;
import java.util.Set;

public interface CategoryService {

    public Category addCategory(Category category);
    public Category updateCategory(Category category);
    public Category getCategoryById(Long id);
    public Set<Category> getCategories();
    public void deleteCategory(Long id);
}
