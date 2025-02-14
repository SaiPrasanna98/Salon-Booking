package com.vendor.repository;

import com.vendor.model.Vendor;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VendorRepository extends JpaRepository<Vendor,Long> {
    Vendor findByOwnerId(Long id);
    //@Query("select s from Vendor where" +
      //             "(lower(s.name)like lower(concat('%',:keyword,'%') ) OR " +
        //            "lower(s.city)like lower(concat('%',:keyword,'%') ) OR " +
          //          "(lower(s.address)like lower(concat('%',:keyword,'%') ) )"
            //)
    @Query("SELECT s FROM Vendor s WHERE LOWER(s.name) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(s.city) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(s.address) LIKE LOWER(CONCAT('%', :keyword, '%'))")


    List<Vendor> searchVendors(@Param("keyword")String keyword);
}
