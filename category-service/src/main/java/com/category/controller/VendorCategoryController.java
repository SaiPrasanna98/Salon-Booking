package com.category.controller;

import com.category.dto.VendorDTO;
import com.category.modal.Category;
import com.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/categories/vendor-owner")

public class VendorCategoryController {
    private final CategoryService categoryService;
    @PostMapping()
    public ResponseEntity<Category> createCategory(
            @RequestBody Category category
    ) {
        VendorDTO vendorDTO=new VendorDTO();
        vendorDTO.setId(1L);
        Category savedCategory=categoryService.saveCategory(category,vendorDTO);
        return ResponseEntity.ok(savedCategory);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(
            @PathVariable Long id
    ) throws Exception{
        VendorDTO vendorDTO=new VendorDTO();
        vendorDTO.setId(1L);
        categoryService.deleteCategoryById(id,vendorDTO.getId());
        return ResponseEntity.ok("category deleted successfully");
    }
}
