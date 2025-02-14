package com.example.service.impl;

import com.example.dto.CategoryDTO;
import com.example.dto.ServiceDTO;
import com.example.dto.VendorDTO;
import com.example.modal.ServiceOffering;
import com.example.repository.ServiceOfferingRepository;
import com.example.service.ServiceOfferingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class ServiceOfferingServiceImpl implements ServiceOfferingService {

    private final ServiceOfferingRepository serviceOfferingRepository;
    @Override
    public ServiceOffering createService(VendorDTO vendorDTO, ServiceDTO serviceDTO, CategoryDTO categoryDTO) {
        ServiceOffering serviceOffering=new ServiceOffering();
        serviceOffering.setImage(serviceDTO.getImage());
        serviceOffering.setVendorId(vendorDTO.getId());
        serviceOffering.setName(serviceDTO.getName());
        serviceOffering.setDescription(serviceDTO.getDescription());
        serviceOffering.setCategory(categoryDTO.getId());
        serviceOffering.setPrice(serviceDTO.getPrice());
        serviceOffering.setDuration(serviceDTO.getDuration());
        return serviceOfferingRepository.save(serviceOffering);
    }

    @Override
    public ServiceOffering updateService(Long serviceId, ServiceOffering service) throws Exception {
        ServiceOffering serviceOffering=serviceOfferingRepository.findById(serviceId).orElse(null);
        if(serviceOffering==null){
            throw new Exception("service not exist with id"+serviceId);
        }
        serviceOffering.setImage(service.getImage());
        serviceOffering.setName(service.getName());
        serviceOffering.setDescription(service.getDescription());
        serviceOffering.setPrice(service.getPrice());
        serviceOffering.setDuration(service.getDuration());
        return serviceOfferingRepository.save(serviceOffering);
    }

    @Override
    public Set<ServiceOffering> getAllServiceByVendorId(Long vendorId, Long categoryId) {
        Set<ServiceOffering> services=serviceOfferingRepository.findByVendorId(vendorId);
        if(categoryId!=null){
            services=services.stream().filter((service)->service.getCategory()!=null &&
                    service.getCategory()==categoryId).collect(Collectors.toSet());
        }
        return services;
    }

    @Override
    public Set<ServiceOffering> getServicesByIds(Set<Long> ids) {
        List<ServiceOffering> services=serviceOfferingRepository.findAllById(ids);
        return new HashSet<>(services);

    }

    @Override
    public ServiceOffering getServiceById(Long id) throws Exception {
        ServiceOffering serviceOffering=serviceOfferingRepository.findById(id).orElse(null);
        if(serviceOffering==null){
            throw new Exception("service not exist with id"+id);
        }
        return serviceOffering;
    }
}
