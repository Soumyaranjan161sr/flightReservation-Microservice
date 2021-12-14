package com.soumya.reserveFlight.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soumya.reserveFlight.entities.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
