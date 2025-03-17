package com.anand.category.serviceImpl;

import com.anand.category.entity.Category;
import com.anand.category.payload.OnlineAppDto;
import com.anand.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    @Override
    public Category createCategory(Category category, OnlineAppDto onlineAppDto) {
        return null;
    }

    @Override
    public Set<Category> getAllCategoriesByOnlineAppId(Long id) {
        return Set.of();
    }

    @Override
    public Category getCategoryById(Long id) {
        return null;
    }

    @Override
    public void deleteCategoryById(Long id) {

    }
}
