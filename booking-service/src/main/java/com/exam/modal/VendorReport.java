package com.exam.modal;

import lombok.Data;

@Data
public class VendorReport {
    private Long vendorId;
    private String vendorName;
    private int totalEarnings;
    private Integer totalBookings;
    private Integer cancelledBookings;
    private Double totalRefund;

}
