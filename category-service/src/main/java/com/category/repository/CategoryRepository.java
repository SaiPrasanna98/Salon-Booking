package com.category.repository;

import com.category.modal.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Set<Category> findByVendorId(Long vendorId);
}
