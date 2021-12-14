package com.soumya.reserveFlight.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soumya.reserveFlight.dto.ReservationRequest;
import com.soumya.reserveFlight.entities.Flight;
import com.soumya.reserveFlight.entities.Passenger;
import com.soumya.reserveFlight.entities.Reservation;
import com.soumya.reserveFlight.repos.FlightRepository;
import com.soumya.reserveFlight.repos.PassengerRepository;
import com.soumya.reserveFlight.repos.ReservationRepository;
import com.soumya.reserveFlight.util.EmailUtil;
import com.soumya.reserveFlight.util.PDFGenerator;

@Service
public class ReservationServiceImpl implements ReservationService{
	@Autowired
	FlightRepository flightRepository;
	
	@Autowired
	PassengerRepository passengerRepository;
	
	@Autowired
	ReservationRepository reservationRepository;
	@Autowired
	PDFGenerator pdfGenerator;
	@Autowired
	EmailUtil emailUtil;
	
	@Override
	@Transactional
	public Reservation bookFlight(ReservationRequest request) {
		
		// Make Payment
		
		Long flightId = request.getFlightId();
		Flight flight = flightRepository.findById(flightId).get();
		
		Passenger passenger = new Passenger();
		passenger.setFirstName(request.getPassengerFirstName());
		passenger.setLastName(request.getPassengerLastName());
		passenger.setPhone(request.getPassengerPhone());
		passenger.setEmail(request.getPassengerEmail());
		Passenger savedPassenger = passengerRepository.save(passenger);
		
		Reservation reservation = new Reservation();
		reservation.setFlight(flight);
		reservation.setPassenger(savedPassenger);
		reservation.setCheckedIn(false);
		
		Reservation savedReservation = reservationRepository.save(reservation);
		
		String filePath = "/Users/aastha/Desktop/reservations/reservation"+savedReservation.getId()+".pdf";
				pdfGenerator.generateItinerary(savedReservation, filePath);
		
				emailUtil.sendItinerary(passenger.getEmail(), filePath);
		return savedReservation;
	}
}
