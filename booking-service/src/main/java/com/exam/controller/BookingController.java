package com.exam.controller;

import com.exam.domain.BookingStatus;
import com.exam.dto.*;
import com.exam.mapper.BookingMapper;
import com.exam.modal.Booking;
import com.exam.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {
    private final BookingService bookingService;
    @PostMapping
    public ResponseEntity<Booking> createBooking(
            @RequestParam Long vendorId,
            @RequestBody BookingRequest bookingRequest
            ) throws Exception {
        UserDTO user = new UserDTO();
        user.setId(1L);
        VendorDTO vendor = new VendorDTO();
        vendor.setId(vendorId);
        Set<ServiceDTO> serviceDTOSet = new HashSet<>();
        ServiceDTO serviceDTO = new ServiceDTO();
        serviceDTO.setId(1L);
        serviceDTO.setPrice(399);
        serviceDTO.setDuration(45);
        serviceDTO.setName("Hair cut");
        serviceDTOSet.add(serviceDTO);
        Booking booking = bookingService.createBooking(bookingRequest,
                user,
                vendor,
                serviceDTOSet);

        return ResponseEntity.ok(booking);
    }
        @GetMapping("/customer")
        public ResponseEntity<Set<BookingDTO>> getBookingsByCustomer(

                ){
            List<Booking> bookings=bookingService.getBookingsByCustomer(1L);
            return ResponseEntity.ok(getBookingDTOs(bookings));
        }
    @GetMapping("/{bookingId}")
    public ResponseEntity<BookingDTO> getBookingsById(
            @PathVariable Long bookingId

    ) throws Exception{
        Booking booking=bookingService.getBookingsById(bookingId);
        return ResponseEntity.ok(BookingMapper.toDTO(booking));

    }
    @PutMapping("/{bookingId}/status")
    public ResponseEntity<BookingDTO> updateBookingStatus(
            @PathVariable Long bookingId,
            @RequestParam BookingStatus status

            ) throws Exception{
        Booking booking=bookingService.updateBooking(bookingId,status);
        return ResponseEntity.ok(BookingMapper.toDTO(booking));

    }
    @GetMapping("/slots/vendor/{vendorId}/date/{date}")
    public ResponseEntity<List<BookingSlotDTO>> getBookingSlot(
            @PathVariable Long vendorId,
            @RequestParam LocalDate date

    ) throws Exception{
        List<Booking> bookings=bookingService.getBookingsByDate(date,vendorId);
        List<BookingSlotDTO> slotDTOS=bookings.stream()
                .map(booking -> {
                    BookingSlotDTO slotDTO=new BookingSlotDTO();
                    slotDTO.setStartTime(booking.getStartTime());
                    return slotDTO;
                }).collect(Collectors.toList());
        return ResponseEntity.ok(slotDTOS);

    }
        private Set<BookingDTO> getBookingDTOs(List<Booking> bookings){
            return bookings.stream().
                    map(booking-> {
                        return BookingMapper.toDTO(booking);
                    }).collect(Collectors.toSet());
                    }
        }




