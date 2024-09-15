package com.linktic.reservation_system.controller;

import com.linktic.reservation_system.dto.ReservationDTO;
import com.linktic.reservation_system.exception.UserAlreadyExistsException;
import com.linktic.reservation_system.model.Reservation;
import com.linktic.reservation_system.model.User;
import com.linktic.reservation_system.service.ReservationService;
import com.linktic.reservation_system.service.TokenService;
import com.linktic.reservation_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<Reservation>> getAllReservations(@RequestHeader("Authorization") String token) {
        validateToken(token);
        List<Reservation> reservations = reservationService.getAllReservations();
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Reservation> createReservation(@RequestBody ReservationDTO reservationDTO) {
        try {
            User user = userService.findById(reservationDTO.getUserId())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado con el ID: " + reservationDTO.getUserId()));
            Reservation reservation = new Reservation();
            reservation.setReservationDetails(reservationDTO.getReservationDetails());
            reservation.setReservationDate(reservationDTO.getReservationDate());
            reservation.setUser(user);

            Reservation createdReservation = reservationService.createReservation(reservation);
            return new ResponseEntity<>(createdReservation, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservationById(
            @PathVariable Long id,
            @RequestHeader("Authorization") String token) {
        // Validar token
        validateToken(token);

        try {
            Optional<Reservation> reservation = reservationService.getReservationById(id);
            if (reservation.isPresent()) {
                return new ResponseEntity<>(reservation.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateReservation(
            @PathVariable Long id,
            @RequestBody ReservationDTO reservationDTO,
            @RequestHeader("Authorization") String token) {

        // Validar token
        validateToken(token);

        try {
            // Validar usuario
            User user = userService.findById(reservationDTO.getUserId())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado con el ID: " + reservationDTO.getUserId()));

            // Crear la reserva para actualizar
            Reservation reservation = new Reservation();
            reservation.setId(id);
            reservation.setReservationDetails(reservationDTO.getReservationDetails());
            reservation.setReservationDate(reservationDTO.getReservationDate());
            reservation.setUser(user);

            // Actualizar la reserva
            Reservation updatedReservation = reservationService.updateReservation(reservation);
            return new ResponseEntity<>(updatedReservation, HttpStatus.OK);
        } catch (UserAlreadyExistsException e) {
            // Devolver un mensaje específico y NOT_FOUND status
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (RuntimeException e) {
            // Manejar otras excepciones
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> cancelledReservation(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        validateToken(token);
        try {
            reservationService.cancelledReservation(id);
            return new ResponseEntity<>("Reservación eliminada correctamente.", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    private void validateToken(String token) {
        if (token == null || !tokenService.validateToken(token)) {
            throw new RuntimeException("Inautorizado");
        }
    }
}
