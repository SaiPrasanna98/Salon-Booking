package com.vendor.mapper;

import com.vendor.dto.VendorDTO;
import com.vendor.model.Vendor;

public class VendorMapper {
    public static VendorDTO mapToDTO(Vendor vendor){
        VendorDTO vendorDTO=new VendorDTO();
        vendorDTO.setId(vendor.getId());
        vendorDTO.setCity(vendor.getCity());
        vendorDTO.setEmail(vendor.getEmail());
        vendorDTO.setImages(vendor.getImages());
        vendorDTO.setAddress(vendor.getAddress());
        vendorDTO.setOpenTime(vendor.getOpenTime());
        vendorDTO.setCloseTime(vendor.getCloseTime());
        vendorDTO.setName(vendor.getName());
        vendorDTO.setPhoneNumber(vendor.getPhoneNumber());
        vendorDTO.setOwnerId(vendor.getOwnerId());
        return vendorDTO;

    }
}
