package com.example.controller;

import com.example.dto.CategoryDTO;
import com.example.dto.ServiceDTO;
import com.example.dto.VendorDTO;
import com.example.modal.ServiceOffering;
import com.example.service.ServiceOfferingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/service-offering/vendor-owner")

public class VendorServiceOfferingController {
    private final ServiceOfferingService serviceOfferingService;
    @PostMapping
    public ResponseEntity<ServiceOffering> createService(
            @RequestBody ServiceDTO serviceDTO
            ){
        VendorDTO vendorDTO=new VendorDTO();
        vendorDTO.setId(1L);
        CategoryDTO categoryDTO=new CategoryDTO();
        categoryDTO.setId(serviceDTO.getCategory());
        ServiceOffering serviceOffering=serviceOfferingService.createService(vendorDTO,serviceDTO,categoryDTO);
        return ResponseEntity.ok(serviceOffering);
    }
    @PostMapping("/{id}")
    public ResponseEntity<ServiceOffering> updateService(
            @PathVariable Long id,
            @RequestBody ServiceOffering serviceOffering
    ) throws Exception {
       // VendorDTO vendorDTO=new VendorDTO();
       // vendorDTO.setId(1L);
       // CategoryDTO categoryDTO=new CategoryDTO();
       // categoryDTO.setId(1L);
        ServiceOffering serviceOfferings=serviceOfferingService.updateService(id,serviceOffering);
        return ResponseEntity.ok(serviceOfferings);
    }

}
