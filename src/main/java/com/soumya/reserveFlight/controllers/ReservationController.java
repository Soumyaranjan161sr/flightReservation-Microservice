package com.soumya.reserveFlight.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.soumya.reserveFlight.dto.ReservationRequest;
import com.soumya.reserveFlight.entities.Flight;
import com.soumya.reserveFlight.entities.Reservation;
import com.soumya.reserveFlight.repos.FlightRepository;
import com.soumya.reserveFlight.services.ReservationService;

@Controller
public class ReservationController {
	@Autowired
	FlightRepository flightRepository;
	
	@Autowired
	ReservationService reservationService;
	
	@RequestMapping("/showCompleteReservation")
	public String showCompleteReservation(@RequestParam("flightId") Long flightId, ModelMap modelMap)
	{
		Flight flight = flightRepository.findById(flightId).get();
		modelMap.addAttribute("flight", flight);
		return "completeReservation";
	}
	
	@RequestMapping(value = "/completeReservation", method = RequestMethod.POST)
		public String completeReservation(ReservationRequest request, ModelMap modelMap)
		{
		
		Reservation reservation = reservationService.bookFlight(request);
		modelMap.addAttribute("msg", "Reservation created successfully and the id is " + reservation.getId());
		return "reservationConfirmation";
		}     
}
