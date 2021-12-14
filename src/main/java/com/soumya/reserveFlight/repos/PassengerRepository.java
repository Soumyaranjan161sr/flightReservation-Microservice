package com.soumya.reserveFlight.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soumya.reserveFlight.entities.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {

}
