package com.category.modal;

import com.category.dto.VendorDTO;
import com.category.repository.CategoryRepository;
import com.category.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
@Service
@RequiredArgsConstructor


public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public Category saveCategory(Category category, VendorDTO vendorDTO) {
        Category newCategory=new Category();
        newCategory.setName(category.getName());
        newCategory.setVendorId(vendorDTO.getId());
        newCategory.setImage(category.getImage());
        return categoryRepository.save(newCategory);
    }

    @Override
    public Set<Category> getAllCategoriesByVendor(Long id) {
        return categoryRepository.findByVendorId(id);
    }

    @Override
    public Category getCategoryById(Long id) throws Exception {
        Category category=categoryRepository.findById(id).orElse(null);
        if (category==null){
            throw new Exception("category not exist with id" + id);
        }
        return category;
    }

    @Override
    public void deleteCategoryById(Long id,Long vendorId) throws Exception {
        Category category=getCategoryById(id);
        if (category.getVendorId().equals(vendorId)){
            throw new Exception("you don't have permission to delete this category");
        }
        categoryRepository.deleteById(id);

    }
}
