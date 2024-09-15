package com.linktic.reservation_system.controller;

import com.linktic.reservation_system.model.Reservation;
import com.linktic.reservation_system.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @GetMapping("/user/{userId}")
    public List<Reservation> listReservation(@PathVariable Long userId) {
        return reservationService.findReservationsByUserId(userId);
    }

    @PostMapping
    public Reservation createReservation(@RequestBody Reservation reservation) {
        return reservationService.createReservation(reservation);
    }

    @PutMapping("/{id}")
    public Reservation updateReservation(@PathVariable Long id, @RequestBody Reservation reservation) {
        reservation.setId(id);
        return reservationService.updateReservation(reservation);
    }

    @DeleteMapping("/{id}")
    public void cancelledReservation(@PathVariable Long id) {
        reservationService.cancelledReservation(id);
    }
}
