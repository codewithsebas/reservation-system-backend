package com.linktic.reservation_system.specification;

import com.linktic.reservation_system.model.Reservation;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class ReservationSpecifications {

    public static Specification<Reservation> hasUsername(String username) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("user").get("username"), "%" + username + "%");
    }

    public static Specification<Reservation> hasServiceTitle(String serviceTitle) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("serviceTitle"), "%" + serviceTitle + "%");
    }

    public static Specification<Reservation> reservationDateBetween(LocalDate startDate, LocalDate endDate) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.between(root.get("reservationDate"), startDate, endDate);
    }
}
