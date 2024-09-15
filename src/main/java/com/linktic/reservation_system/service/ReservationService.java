package com.linktic.reservation_system.service;

import com.linktic.reservation_system.exception.UserAlreadyExistsException;
import com.linktic.reservation_system.model.Reservation;
import com.linktic.reservation_system.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> findReservationsByUserId(Long userId) {
        return reservationRepository.findByUserId(userId);
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation createReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public Reservation updateReservation(Reservation reservation) {
        Optional<Reservation> existingReservation = reservationRepository.findById(reservation.getId());

        if (existingReservation.isPresent()) {
            Reservation updatedReservation = existingReservation.get();
            updatedReservation.setReservationDetails(reservation.getReservationDetails());
            updatedReservation.setReservationDate(reservation.getReservationDate());
            updatedReservation.setUser(reservation.getUser());
            return reservationRepository.save(updatedReservation);
        } else {
            throw new UserAlreadyExistsException("Reserva no encontrada con el ID: " + reservation.getId());
        }
    }

    public Optional<Reservation> getReservationById(Long id) {
        return reservationRepository.findById(id);
    }



    public void cancelledReservation(Long id) {
        Optional<Reservation> reservation = reservationRepository.findById(id);
        if (reservation.isPresent()) {
            reservationRepository.deleteById(id);
        } else {
            throw new RuntimeException("Reserva no encontrada");
        }
    }
}
