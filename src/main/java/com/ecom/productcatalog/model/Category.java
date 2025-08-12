package com.ecom.productcatalog.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity(name = "categories")// to rename the table name in the h2 database earlier it was the same as the classname
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long categoryID;

    @NotBlank
    @Size(min =5, message = "Category name must contain atleast 5 characters")
    private String categoryName;

    public Category(Long categoryId, String categoryName) {
        this.categoryID = categoryId;
        this.categoryName = categoryName;
    }

    public Category() {
    }

    public Long getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Long categoryID) {
        this.categoryID = categoryID;
    }

    public @NotBlank @Size(min = 5, message = "Category name must contain atleast 5 characters") String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(@NotBlank @Size(min = 5, message = "Category name must contain atleast 5 characters") String categoryName) {
        this.categoryName = categoryName;
    }
}
