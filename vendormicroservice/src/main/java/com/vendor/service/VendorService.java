package com.vendor.service;
import java.util.List;
import com.vendor.dto.UserDTO;
import com.vendor.dto.VendorDTO;
import com.vendor.model.Vendor;

public interface VendorService {
    Vendor createVendor(VendorDTO vendor, UserDTO user);
    Vendor updateVendor(VendorDTO vendor,UserDTO user,Long vendorId) throws Exception;
    List<Vendor> getAllVendors();
    Vendor getVendorById(Long vendorId) throws Exception;
    Vendor getVendorByOwnerId(Long ownerId);
    List<Vendor> searchVendorByCIty(String city);
}
