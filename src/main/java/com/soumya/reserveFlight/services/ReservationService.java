package com.soumya.reserveFlight.services;

import com.soumya.reserveFlight.dto.ReservationRequest;
import com.soumya.reserveFlight.entities.Reservation;

public interface ReservationService {
	public Reservation bookFlight(ReservationRequest request);
}
