package com.ecom.productcatalog.service;

import com.ecom.productcatalog.model.Category;
import com.ecom.productcatalog.payload.CategoryDTO;
import com.ecom.productcatalog.payload.CategoryResponse;

import java.util.List;

public interface CategoryService {
   CategoryResponse getAllCategory(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);
    CategoryDTO createCategory(CategoryDTO categoryDTO);

    CategoryDTO deleteCategory(Long categoryId);

    CategoryDTO updateCategory(Long categoryId, CategoryDTO updatedCategoryDTO);
}
