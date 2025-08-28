package com.ecom.productcatalog.controller;


import com.ecom.productcatalog.config.AppConstants;
import com.ecom.productcatalog.model.Category;
import com.ecom.productcatalog.payload.CategoryDTO;
import com.ecom.productcatalog.payload.CategoryResponse;
import com.ecom.productcatalog.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<CategoryResponse> getAllCategories(
            @RequestParam(name="pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(name="pageSize",defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(name = "sortBy",defaultValue = AppConstants.SORT_CATEGORIES_BY, required = false) String sortBy,
            @RequestParam(name = "sortOrder",defaultValue = AppConstants.SORT_DIR, required = false) String sortOrder) {
        CategoryResponse categoryResponse = categoryService.getAllCategory(pageNumber,pageSize,sortBy,sortOrder);
        return new ResponseEntity<>(categoryResponse, HttpStatus.OK);
    }
//    @GetMapping("/api/public/echo")
//    public ResponseEntity<String> echoMessage(@RequestParam(name = "message", defaultValue = "Hello world") String message){
//        return new ResponseEntity<>("Echoed message: " + message, HttpStatus.OK);
//    }

    @PostMapping("/api/public/categories")
    public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO){
        CategoryDTO savedCategoryDTO = categoryService.createCategory(categoryDTO);
        return new ResponseEntity<>(savedCategoryDTO, HttpStatus.OK);
    }
    @DeleteMapping("/api/admin/categories/{categoryId}")
    //@RequestMapping(value = "/api" , method = RequestMethod.GET)
    public ResponseEntity<CategoryDTO> deleteCategory(@PathVariable Long categoryId){

           CategoryDTO deletedCategory = categoryService.deleteCategory(categoryId);
           return new ResponseEntity<>(deletedCategory, HttpStatus.OK);
    }

    @PutMapping("/api/admin/categories/{categoryId}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long categoryId , @RequestBody CategoryDTO updatedCategoryDTO) {

            CategoryDTO savedCategoryDTO = categoryService.updateCategory(categoryId, updatedCategoryDTO);
            return new ResponseEntity<>(savedCategoryDTO, HttpStatus.OK);
    }
}
