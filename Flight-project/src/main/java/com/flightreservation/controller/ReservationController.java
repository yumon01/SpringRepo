package com.flightreservation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.flightreservation.dto.ReservationRequest;
import com.flightreservation.entity.Flight;
import com.flightreservation.entity.Reservation;
import com.flightreservation.repository.FlightRepository;
import com.flightreservation.service.ReservationService;

@Controller
public class ReservationController {
	@Autowired
	FlightRepository flightrepository;
	
	@Autowired
	ReservationService reservationService;
	
	@RequestMapping("/showCompleteReservation")
	public String showCompleteReservation(@RequestParam("flightId")Long flightId,ModelMap modelMap) {
		Flight flight=flightrepository.findById(flightId).get();
		modelMap.addAttribute("flight",flight);
		return "completeReservation";
	}
	
	@PostMapping("/completeReservation")
	public String completeReservation(ReservationRequest request,ModelMap modelMap) {
		Reservation reservation = reservationService.bookFlight(request);
		modelMap.addAttribute("msg","Reservation created successfully and the id is"
				+ reservation.getId());
		return "reservationConfirmation";
		
	}
	
	
}
