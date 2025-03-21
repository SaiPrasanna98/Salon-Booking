package com.example.repository;

import com.example.modal.ServiceOffering;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Set;

public interface ServiceOfferingRepository  extends JpaRepository<ServiceOffering,Long> {
    Set<ServiceOffering> findByVendorId(Long vendorId);
}
