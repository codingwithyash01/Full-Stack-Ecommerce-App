package com.ecom.productcatalog.service;

import com.ecom.productcatalog.exceptions.APIExceptions;
import com.ecom.productcatalog.exceptions.ResourceNotFoundException;
import com.ecom.productcatalog.model.Category;
import com.ecom.productcatalog.payload.CategoryDTO;
import com.ecom.productcatalog.payload.CategoryResponse;
import com.ecom.productcatalog.repositories.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService{


    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryResponse getAllCategory() {
        List<Category> categories = categoryRepository.findAll();
        if (categories.isEmpty()) throw new APIExceptions("No Category created till now");
        List<CategoryDTO> categoryDTOS = categories.stream().map(category -> modelMapper.map(category,CategoryDTO.class)).toList();
        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setContent(categoryDTOS);
        return categoryResponse;
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = modelMapper.map(categoryDTO, Category.class);
        Category categoryFromDb = categoryRepository.findByCategoryName(category.getCategoryName());
        if(categoryFromDb!=null) throw new APIExceptions("Category with the name " + category.getCategoryName() + " already exists");
       Category savedCategory = categoryRepository.save(category);
        return modelMapper.map(savedCategory, CategoryDTO.class);

    }

    @Override
    public String deleteCategory(Long categoryId) {
        Category category= categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "categoryId", categoryId));
        categoryRepository.delete(category);
        return "Category with categoryId: " + categoryId + " deleted successfully";
    }

    @Override
    public Category updateCategory(Long categoryId, Category updatedCategory) {
       Category savedCategory = categoryRepository.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "categoryId", categoryId));

        updatedCategory.setCategoryID(categoryId);
        savedCategory = categoryRepository.save(updatedCategory);
        return savedCategory;

    }

}
