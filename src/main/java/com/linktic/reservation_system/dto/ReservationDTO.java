package com.linktic.reservation_system.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class ReservationDTO {
    private Long userId;
    private LocalDate reservationDate;
    private String reservationDetails;
    private String serviceTitle;
}
