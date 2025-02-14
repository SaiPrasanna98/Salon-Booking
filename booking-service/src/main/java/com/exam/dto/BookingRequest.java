package com.exam.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;
@Data
public class BookingRequest {
    private LocalDateTime startTime;
    private LocalDateTime endRime;
    private Set<Long> serviceIds;
}
