package com.ecom.productcatalog.controller;


import com.ecom.productcatalog.model.Category;
import com.ecom.productcatalog.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
@RestController
public class CategoryController {

   private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/api/public/categories")
    public ResponseEntity<List<Category>> getAllCategories() {
        return new ResponseEntity<>(categoryService.getAllCategory(), HttpStatus.OK);
    }
    @PostMapping("/api/public/categories")
    public ResponseEntity<String> createCategory(@Valid @RequestBody Category category){
        categoryService.createCategory(category);
        return new ResponseEntity<>("Category added successfully", HttpStatus.OK);
    }
    @DeleteMapping("/api/admin/categories/{categoryId}")
    //@RequestMapping(value = "/api" , method = RequestMethod.GET)
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId){
       try{
           String status = categoryService.deleteCategory(categoryId);
           return ResponseEntity.status(HttpStatus.OK).body(status);
       }
       catch (ResponseStatusException e){
           return new ResponseEntity<>(e.getReason(), e.getStatusCode());
       }
    }

    @PutMapping("/api/admin/categories/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable Long id , @RequestBody Category updatedCategory) {
        try {
            Category savedCategory = categoryService.updateCategory(id, updatedCategory);
            return new ResponseEntity<>("Category with category id: " + id, HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        }
    }
}
