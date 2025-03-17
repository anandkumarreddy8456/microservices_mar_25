package com.anand.category.Controller;

import com.anand.category.entity.Category;
import com.anand.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    @GetMapping("/getCategoriesByOnlineAppId")
    public ResponseEntity<Set<Category>> getCategoriesByOnlineAppId(@RequestParam Long id){
            return new ResponseEntity<>(categoryService.getAllCategoriesByOnlineAppId(id), HttpStatus.OK);
    }
    @GetMapping("getCategoryById")
    public ResponseEntity<Category> getCategoryById(@RequestParam Long id) throws Exception {
        return new ResponseEntity<>(categoryService.getCategoryById(id),HttpStatus.OK);
    }
}
