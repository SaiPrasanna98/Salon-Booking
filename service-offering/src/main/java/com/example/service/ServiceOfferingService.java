package com.example.service;

import com.example.dto.CategoryDTO;
import com.example.dto.ServiceDTO;
import com.example.dto.VendorDTO;
import com.example.modal.ServiceOffering;



import java.util.Set;

public interface ServiceOfferingService {

        ServiceOffering createService(VendorDTO vendorDTO,
                                      ServiceDTO serviceDTO,
                                      CategoryDTO categoryDTO);
        ServiceOffering updateService(Long serviceId,ServiceOffering service) throws Exception;
        Set<ServiceOffering> getAllServiceByVendorId(Long vendorId, Long categoryId);
        Set<ServiceOffering> getServicesByIds(Set<Long> ids);
        ServiceOffering getServiceById(Long id) throws Exception;



    }

