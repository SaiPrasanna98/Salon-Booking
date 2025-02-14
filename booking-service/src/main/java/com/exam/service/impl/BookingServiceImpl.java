package com.exam.service.impl;

import com.exam.domain.BookingStatus;
import com.exam.dto.BookingRequest;
import com.exam.dto.ServiceDTO;
import com.exam.dto.UserDTO;
import com.exam.dto.VendorDTO;
import com.exam.modal.Booking;
import com.exam.modal.VendorReport;
import com.exam.repository.BookingRepository;
import com.exam.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;

    @Override
    public Booking createBooking(BookingRequest booking, UserDTO user, VendorDTO vendor, Set<ServiceDTO> serviceDTOSet) throws Exception {
        int totalDuration = serviceDTOSet.stream()
                .mapToInt(ServiceDTO::getDuration)
                .sum();
        LocalDateTime bookingStartTime = booking.getStartTime();
        LocalDateTime bookingEndTime = bookingStartTime.plusMinutes(totalDuration);
        Boolean isSlotAvailable = isTimeSlotAvailable(vendor, bookingStartTime, bookingEndTime);
        int totalPrice = serviceDTOSet.stream()
                .mapToInt(ServiceDTO::getPrice)
                .sum();
        Set<Long> idList = serviceDTOSet.stream()
                .map(ServiceDTO::getId)
                .collect(Collectors.toSet());
        Booking newBooking = new Booking();
        newBooking.setCustomerId(user.getId());
        newBooking.setVendorId(vendor.getId());
        newBooking.setServiceIds(idList);
        newBooking.setStatus(BookingStatus.PENDING);
        newBooking.setStartTime(bookingStartTime);
        newBooking.setEndTime(bookingEndTime);
        newBooking.setTotalPrice();
        return bookingRepository.save(newBooking);
    }

    public Boolean isTimeSlotAvailable(VendorDTO vendorDTO,
                                       LocalDateTime bookingStartTime,
                                       LocalDateTime bookingEndTime) throws Exception {
        List<Booking> existingBookings = getBookingsByVendor(vendorDTO.getId());
        LocalDateTime vendorOpenTime = vendorDTO.getOpenTime().atDate(bookingStartTime.toLocalDate());
        LocalDateTime vendorCloseTime = vendorDTO.getCloseTime().atDate(bookingEndTime.toLocalDate());
        if (bookingStartTime.isBefore(vendorOpenTime)
                || bookingEndTime.isAfter(vendorCloseTime)) {
            throw new Exception("Booking time must be within Vendor's working Time");
        }
        for (Booking existingBooking : existingBookings) {
            LocalDateTime existingBookingStartTime = existingBooking.getStartTime();
            LocalDateTime existingBookingEndTime = existingBooking.getEndTime();
            if (bookingStartTime.isBefore(existingBookingEndTime)
                    && bookingEndTime.isAfter(existingBookingStartTime)) {
                throw new Exception("slot not available,choose different time");
            }
            if (bookingStartTime.isEqual(existingBookingStartTime)
                    || bookingEndTime.isEqual(existingBookingEndTime)) {
                throw new Exception("slot not available,choose different time");

            }
        }
        return true;
    }

    @Override
    public List<Booking> getBookingsByCustomer(Long customerId) {
        return bookingRepository.findByCustomerId(customerId);
    }

    @Override
    public List<Booking> getBookingsByVendor(Long vendorId) {
        return bookingRepository.findByVendorId(vendorId);
    }

    @Override
    public Booking getBookingsById(Long id) throws Exception {
        Booking booking = bookingRepository.findById(id).orElse(null);
        if (booking == null) {
            throw new Exception("booking not found");
        }
        return booking;
    }

    @Override
    public Booking updateBooking(Long bookingId, BookingStatus status) throws Exception {
        Booking booking=getBookingsById(bookingId);
        booking.setStatus(status);
        return bookingRepository.save(booking);
    }

    @Override
    public List<Booking> getBookingsByDate(LocalDate date, Long vendorId) {
        List<Booking> allBookings = getBookingsByVendor(vendorId);
        if (date == null){
            return allBookings;
    }
     return allBookings.stream()
            .filter(booking-> isSameDate(booking.getStartTime(),date) ||
                    isSameDate(booking.getEndTime(),date)).collect(Collectors.toList());
    }

    private boolean isSameDate(LocalDateTime dateTime, LocalDate date) {
        return dateTime.toLocalDate().isEqual(date);
    }

    @Override
    public VendorReport getVendorReport(Long vendorId) {
        List<Booking> bookings=getBookingsByVendor(vendorId);
        int totalEarnings=bookings.stream()
                .mapToInt(Booking::getTotalPrice)
                .sum();
        Integer totalBooking=bookings.size();
        List<Booking> cancelledBookings=bookings.stream()
                .filter(booking->booking.getStatus().equals(BookingStatus.CANCELLED))
                        .collect(Collectors.toList());
        Double totalRefund=cancelledBookings.stream()
                .mapToDouble(Booking::getTotalPrice)
                .sum();
        VendorReport report=new VendorReport();
        report.setVendorId(vendorId);
        report.setCancelledBookings(cancelledBookings.size());
        report.setTotalBookings(totalEarnings);
        report.setTotalEarnings(totalEarnings);
        report.setTotalRefund(totalRefund);
        report.setTotalBookings(totalBooking);

        return null;


        // @Override
        // public void deleteBooking(Long bookingId) {

    }

}
