package com.ecom.productcatalog.service;

import com.ecom.productcatalog.model.Category;

import java.util.ArrayList;
import java.util.List;

public interface CategoryService {
    List<Category> getAllCategory();
    void createCategory(Category category);

    String deleteCategory(Long categoryId);

    Category updateCategory(Long id, Category updatedCategory);
}
