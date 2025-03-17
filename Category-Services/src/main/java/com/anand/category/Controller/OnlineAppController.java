package com.anand.category.Controller;

import com.anand.category.entity.Category;
import com.anand.category.payload.OnlineAppDto;
import com.anand.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Owner/Category")
public class OnlineAppController {
    private final CategoryService categoryService;
    @PostMapping("/createCategory")
    public ResponseEntity<Category> createCategory(@RequestBody Category category){
        OnlineAppDto onlineAppDto= new OnlineAppDto();
        onlineAppDto.setId(1L);
        return new ResponseEntity<>(categoryService.createCategory(category,onlineAppDto), HttpStatus.OK);
    }
    @DeleteMapping("/deleteCategory")
    public ResponseEntity<String> deleteCategory(@RequestParam Long id) throws Exception {
        OnlineAppDto onlineAppDto= new OnlineAppDto();
        onlineAppDto.setId(1L);
        categoryService.deleteCategoryById(id,onlineAppDto.getId());
         return new ResponseEntity<>("Deleted SuccessFully",HttpStatus.OK);
    }
}
