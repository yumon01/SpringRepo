package com.flightreservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flightreservation.entity.Passenger;
import com.flightreservation.entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long>{

	

}
