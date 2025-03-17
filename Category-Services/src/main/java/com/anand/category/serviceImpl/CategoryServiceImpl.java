package com.anand.category.serviceImpl;

import com.anand.category.entity.Category;
import com.anand.category.payload.OnlineAppDto;
import com.anand.category.repository.CategoryRepository;
import com.anand.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category createCategory(Category category, OnlineAppDto onlineAppDto) {
        Category category1=new Category();
        category1.setId(category.getId());
        category1.setName(category.getName());
        category1.setImage(category.getImage());
        category1.setOnlineAppId(onlineAppDto.getId());
        return categoryRepository.save(category1);
    }

    @Override
    public Set<Category> getAllCategoriesByOnlineAppId(Long id) {

        return categoryRepository.findByOnlineAppId(id);
    }

    @Override
    public Category getCategoryById(Long id) throws Exception {
        Optional<Category> opt=categoryRepository.findById(id);
        if(opt.isPresent()){
            return opt.get();
        }
        throw new Exception("Category Does not Exsit with id"+id);
    }

    @Override
    public void deleteCategoryById(Long id, Long OnlineAppId) throws Exception {
            Optional<Category> opt=categoryRepository.findById(id);
            if(opt.isPresent()){
                if(Objects.equals(opt.get().getOnlineAppId(), OnlineAppId)) {
                    categoryRepository.deleteById(id);
                    return;
                }else{
                    throw new Exception("UnAuthored Person (you don't have person)"+id);
                }
            }
        throw new Exception("Category Does not Exsit with id : "+id);
    }
}
