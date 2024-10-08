package com.flightreservation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightreservation.dto.ReservationRequest;
import com.flightreservation.entity.Flight;
import com.flightreservation.entity.Passenger;
import com.flightreservation.entity.Reservation;
import com.flightreservation.repository.FlightRepository;
import com.flightreservation.repository.PassengerRepository;
import com.flightreservation.repository.ReservationRepository;
import com.flightreservation.util.EmailUtil;
import com.flightreservation.util.PDFGenerator;

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
	public Reservation bookFlight(ReservationRequest request) {

	    Long flightId = request.getFlightId();

	    Flight flight = flightRepository.findById(flightId).get();

	    Passenger passenger = new Passenger();
	    passenger.setFirstName(request.getPassengerFirstName());
	    passenger.setLastName(request.getPassengerLastName());
	    passenger.setPhone(request.getPassengerPhone());
	    passenger.setEmail(request.getPassengerEmail());

	    // store passenger data into table
	    Passenger savedPassenger = passengerRepository.save(passenger);

	    Reservation reservation = new Reservation();
	    reservation.setFlight(flight);
	    reservation.setPassenger(savedPassenger);
	    reservation.setCheckedIn(false);
	    
	    //store reservation data into table
	    Reservation savedReservation =reservationRepository.save(reservation);
		String filePath="C:/Users/Asus/Documents/reservations/reservation"+savedReservation.getId()+".pdf";
	    pdfGenerator.generateItinerary(savedReservation, filePath);
	    emailUtil.sendItinerary(passenger.getEmail(), filePath);
	    return savedReservation;
	}
}
