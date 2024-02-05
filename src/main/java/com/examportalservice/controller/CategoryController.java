package com.examportalservice.controller;

import com.examportalservice.entity.exam.Category;
import com.examportalservice.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
@CrossOrigin("*")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @PostMapping("/add")
    ResponseEntity<?> addCategory(@RequestBody Category category){
        Category category1 = this.categoryService.addCategory(category);
        return   ResponseEntity.ok(category1);
    }

    @GetMapping("/getCategoryById/{id}")
    public Category getCategoryById(@PathVariable("id") Long categoryId){
        return this.categoryService.getCategoryById(categoryId);
    }

    @GetMapping("/all")
    ResponseEntity<?> getAllCategories(){
        return ResponseEntity.ok(this.categoryService.getCategories());
    }
    @PutMapping("/update")
    public Category updateCategory(@RequestBody  Category category){
        return this.categoryService.updateCategory(category);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCategory(@PathVariable("id") Long id){
        this.categoryService.deleteCategory(id);
    }
}
