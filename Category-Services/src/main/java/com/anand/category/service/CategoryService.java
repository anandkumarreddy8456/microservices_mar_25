package com.anand.category.service;

import com.anand.category.entity.Category;
import com.anand.category.payload.OnlineAppDto;

import java.util.Set;

public interface CategoryService {
    Category createCategory(Category category, OnlineAppDto onlineAppDto);
    Set<Category> getAllCategoriesByOnlineAppId(Long id);
    Category getCategoryById(Long id);
    void deleteCategoryById(Long id);

}
