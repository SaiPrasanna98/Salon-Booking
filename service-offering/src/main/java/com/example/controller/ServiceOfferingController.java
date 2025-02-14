package com.example.controller;

import com.example.modal.ServiceOffering;
import com.example.service.ServiceOfferingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Set;
@RestController
@RequestMapping("/api/service-offering")
@RequiredArgsConstructor

public class ServiceOfferingController {
    private final ServiceOfferingService serviceOfferingService;
    @GetMapping("/vendor/{vendorId}")
    public ResponseEntity<Set<ServiceOffering>> getServicesByVendorId(
            @PathVariable Long vendorId,
    @RequestParam(required = false) Long categoryId
            ){
        Set<ServiceOffering> serviceOfferings=serviceOfferingService.getAllServiceByVendorId(vendorId,categoryId);
        return ResponseEntity.ok(serviceOfferings);

    }
    @GetMapping("/{id}")
    public ResponseEntity<ServiceOffering> getServicesById(
            @PathVariable Long id
    ) throws Exception {
        ServiceOffering serviceOffering=serviceOfferingService.getServiceById(id);
        return ResponseEntity.ok(serviceOffering);

    }
    @GetMapping("/list/{ids}")
    public ResponseEntity<Set<ServiceOffering>> getServicesByIds(
            @PathVariable Set<Long> ids
    ){
        Set<ServiceOffering> serviceOfferings=serviceOfferingService.getServicesByIds(ids);
        return ResponseEntity.ok(serviceOfferings);

    }
}
