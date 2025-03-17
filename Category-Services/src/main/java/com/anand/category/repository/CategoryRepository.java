package com.anand.category.repository;

import com.anand.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    Set<Category> findByOnlineAppId(Long id);
}
