package com.linktic.reservation_system.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ReservationDTO {
    private Long id;
    private String reservationDetails;
    private LocalDateTime reservationDate;
}
