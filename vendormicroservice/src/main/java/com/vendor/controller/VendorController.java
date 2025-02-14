package com.vendor.controller;

import com.vendor.dto.UserDTO;
import com.vendor.dto.VendorDTO;
import com.vendor.mapper.VendorMapper;
import com.vendor.model.Vendor;
import com.vendor.service.VendorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api/vendors")
@RequiredArgsConstructor
public class VendorController {
    private final VendorService vendorService;

    //http://localhost:5002/api/vendors
    @PostMapping
    public ResponseEntity<VendorDTO> createVendor(@RequestBody VendorDTO vendorDTO) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        Vendor vendor = vendorService.createVendor(vendorDTO, userDTO);
        VendorDTO vendorDTO1 = VendorMapper.mapToDTO(vendor);
        return ResponseEntity.ok(vendorDTO1);
    }

    //http://localhost:5002/api/vendors/2
    @PutMapping("/{vendorId}")
    public ResponseEntity<VendorDTO> updateVendor(@PathVariable Long vendorId, @RequestBody VendorDTO vendorDTO) throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        //System.out.println("---------"+salonId+"email"+ vendorDTO.getEmail());
        Vendor vendor = vendorService.updateVendor(vendorDTO, userDTO, vendorId);
        VendorDTO vendorDTO1 = VendorMapper.mapToDTO(vendor);
        return ResponseEntity.ok(vendorDTO1);
    }

    //http://localhost:5002/api/vendors
    @GetMapping()
    public ResponseEntity<List<VendorDTO>> getVendors() throws Exception {
        //UserDTO userDTO = new UserDTO();
        //userDTO.setId(1L);
        List<Vendor> vendors = vendorService.getAllVendors();
        List<VendorDTO> vendorDTOS = vendors.stream().map((vendor) ->
                {
                    VendorDTO vendorDTO = VendorMapper.mapToDTO(vendor);
                    return vendorDTO;
                }
        ).toList();
        return ResponseEntity.ok(vendorDTOS);
    }

    //http://localhost:5002/api/vendors/5
    @GetMapping("/{vendorId}")
    public ResponseEntity<VendorDTO> getVendorById(@PathVariable Long vendorId) throws Exception {
        //UserDTO userDTO = new UserDTO();
        //userDTO.setId(1L);
        Vendor vendor = vendorService.getVendorById(vendorId);

        VendorDTO vendorDTO = VendorMapper.mapToDTO(vendor);

        return ResponseEntity.ok(vendorDTO);
    }
    //http://localhost:5002/api/vendors/search?city=mumbai
    @GetMapping("/search")
    public ResponseEntity<List<VendorDTO>> searchVendors(@RequestParam("city") String city) throws Exception {
        //UserDTO userDTO = new UserDTO();
        //userDTO.setId(1L);
        List<Vendor> vendors = vendorService.searchVendorByCIty(city);
        List<VendorDTO> vendorDTOS = vendors.stream().map((vendor) ->
                {
                    VendorDTO vendorDTO = VendorMapper.mapToDTO(vendor);
                    return vendorDTO;
                }
        ).toList();
        return ResponseEntity.ok(vendorDTOS);
    }
    @GetMapping("/owner")
    public ResponseEntity<VendorDTO> getVendorByOwnerId(@PathVariable Long vendorId) throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);
        Vendor vendor = vendorService.getVendorByOwnerId(userDTO.getId());

        VendorDTO vendorDTO = VendorMapper.mapToDTO(vendor);

        return ResponseEntity.ok(vendorDTO);
    }
}

