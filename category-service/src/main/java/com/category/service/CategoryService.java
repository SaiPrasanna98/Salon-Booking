package com.category.service;

import com.category.dto.VendorDTO;
import com.category.modal.Category;

import java.util.Set;

public interface CategoryService {
    Category saveCategory(Category category, VendorDTO vendorDTO);
    Set<Category> getAllCategoriesByVendor(Long id);
    Category getCategoryById(Long id) throws Exception;
    void deleteCategoryById(Long id,Long vendorId) throws Exception;
}
