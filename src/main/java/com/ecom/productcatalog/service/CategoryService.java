package com.ecom.productcatalog.service;

import com.ecom.productcatalog.model.Category;
import com.ecom.productcatalog.payload.CategoryDTO;
import com.ecom.productcatalog.payload.CategoryResponse;

import java.util.List;

public interface CategoryService {
   CategoryResponse getAllCategory();
    CategoryDTO createCategory(CategoryDTO categoryDTO);

    String deleteCategory(Long categoryId);

    Category updateCategory(Long categoryId, Category updatedCategory);
}
