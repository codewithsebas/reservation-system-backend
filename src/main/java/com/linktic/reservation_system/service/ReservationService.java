package com.linktic.reservation_system.service;

import com.linktic.reservation_system.model.Reservation;
import com.linktic.reservation_system.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> findReservationsByUserId(Long userId) {
        return reservationRepository.findByUserId(userId);
    }

    public Reservation createReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public Reservation updateReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public void cancelledReservation(Long id) {
        reservationRepository.deleteById(id);
    }
}
