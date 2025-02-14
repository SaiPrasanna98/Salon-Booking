package com.exam.mapper;

import com.exam.dto.BookingDTO;
import com.exam.modal.Booking;

public class BookingMapper {
    public static BookingDTO toDTO(Booking booking) {
        BookingDTO bookingDTO=new BookingDTO();
        bookingDTO.setId(booking.getId());
        bookingDTO.setCustomerId(booking.getCustomerId());
        bookingDTO.setStatus(booking.getStatus());
        bookingDTO.setEndTime(booking.getEndTime());
        bookingDTO.setStartTime(booking.getStartTime());
        bookingDTO.setVendorId(booking.getVendorId());
        bookingDTO.setServiceIds(booking.getServiceIds());
        return bookingDTO;


    }
}
