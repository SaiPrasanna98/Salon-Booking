package com.vendor.service.impl;

import com.vendor.dto.UserDTO;
import com.vendor.dto.VendorDTO;
import com.vendor.service.VendorService;
import com.vendor.model.Vendor;
import com.vendor.repository.VendorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class VendorServiceImpl implements VendorService {
    private final VendorRepository vendorRepository;
    @Override
    public Vendor createVendor(VendorDTO req, UserDTO user) {
        Vendor vendor= new Vendor();
        vendor.setName(req.getName());
        vendor.setAddress(req.getAddress());
        vendor.setEmail(req.getEmail());
        vendor.setCity(req.getCity());
        vendor.setImages(req.getImages());
        vendor.setOwnerId(user.getId());
        vendor.setOpenTime(req.getOpenTime());
        vendor.setCloseTime(req.getCloseTime());
        vendor.setPhoneNumber(req.getPhoneNumber());
        return vendorRepository.save(vendor);
    }

    @Override
    public Vendor updateVendor(VendorDTO vendor, UserDTO user, Long vendorId) throws Exception {
        Vendor existingVendor= vendorRepository.findById(vendorId).orElse(null);
        if(!vendor.getOwnerId().equals(user.getId())){
            throw new Exception("you don't have permission to update this vendor");
        }
        if(existingVendor!=null&& vendor.getOwnerId().equals(user.getId())){
           existingVendor.setCity(vendor.getCity());
           existingVendor.setEmail(vendor.getEmail());
           existingVendor.setName(vendor.getName());
           existingVendor.setImages(vendor.getImages());
           existingVendor.setAddress(vendor.getAddress());
           existingVendor.setPhoneNumber(vendor.getPhoneNumber());
           existingVendor.setOpenTime(vendor.getOpenTime());
           existingVendor.setCloseTime(vendor.getCloseTime());
           existingVendor.setOwnerId(user.getId());
            return vendorRepository.save(existingVendor);
        }
        throw new Exception("vendor not exist") ;
    }

    @Override
    public List<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }

    @Override
    public Vendor getVendorById(Long vendorId) throws Exception {
        Vendor vendor = vendorRepository.findById(vendorId).orElse(null);
        if (vendor == null) {
            throw new Exception("vendor not exist");
        }
        return vendor;
    }

    @Override
    public Vendor getVendorByOwnerId(Long ownerId) {
        return vendorRepository.findByOwnerId(ownerId);
    }

    @Override
    public List<Vendor> searchVendorByCIty(String city) {
        return vendorRepository.searchVendors(city);
    }
}
