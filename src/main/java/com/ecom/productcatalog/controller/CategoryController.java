package com.ecom.productcatalog.controller;


import com.ecom.productcatalog.model.Category;
import com.ecom.productcatalog.payload.CategoryDTO;
import com.ecom.productcatalog.payload.CategoryResponse;
import com.ecom.productcatalog.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@RestController
public class CategoryController {

   private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/api/public/categories")
    public ResponseEntity<CategoryResponse> getAllCategories() {
        CategoryResponse categoryResponse = categoryService.getAllCategory();
        return new ResponseEntity<>(categoryResponse, HttpStatus.OK);
    }
    @PostMapping("/api/public/categories")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO){
        CategoryDTO savedCategoryDtO = categoryService.createCategory(categoryDTO);
        return new ResponseEntity<>(savedCategoryDtO, HttpStatus.OK);
    }
    @DeleteMapping("/api/admin/categories/{categoryId}")
    //@RequestMapping(value = "/api" , method = RequestMethod.GET)
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId){

           String status = categoryService.deleteCategory(categoryId);
           return ResponseEntity.status(HttpStatus.OK).body(status);
    }

    @PutMapping("/api/admin/categories/{categoryId}")
    public ResponseEntity<String> updateCategory(@PathVariable Long categoryId , @RequestBody Category updatedCategory) {

            Category savedCategory = categoryService.updateCategory(categoryId, updatedCategory);
            return new ResponseEntity<>("Category with category id: " + categoryId, HttpStatus.OK);
    }
}
