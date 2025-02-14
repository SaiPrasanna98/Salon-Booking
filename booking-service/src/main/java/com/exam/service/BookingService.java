package com.exam.service;

import com.exam.domain.BookingStatus;
import com.exam.dto.*;
import com.exam.modal.Booking;
import com.exam.modal.VendorReport;;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;


public interface BookingService {
    Booking createBooking(BookingRequest booking,
                          UserDTO user,
                          VendorDTO vendor,
                          Set<ServiceDTO> serviceDTOSet) throws Exception;

    List<Booking> getBookingsByCustomer(Long customerId);

    List<Booking> getBookingsByVendor(Long vendorId);
    Booking getBookingsById(Long id) throws Exception;
    Booking updateBooking(Long bookingId, BookingStatus status) throws Exception;
    List<Booking> getBookingsByDate(LocalDate date,Long
                                      vendorId);
    VendorReport getVendorReport(Long vendorId);



}
