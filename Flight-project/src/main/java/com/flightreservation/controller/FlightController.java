package com.flightreservation.controller;




import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.flightreservation.entity.Flight;
import com.flightreservation.repository.FlightRepository;

@Controller
public class FlightController{
	@Autowired
	FlightRepository flightRepository;

	//private static final Logger LOGGER = LoggerFactory.getLogger(FlightController.class);
	@GetMapping("findFlights")
	public String showFindFlights() {
		return "findFlights";
	}
	@RequestMapping("findFlights")
	public String findFlights(@RequestParam("from") String from, 
	                          @RequestParam("to") String to, 
	                          @RequestParam("departureDate") @DateTimeFormat(pattern = "MM-dd-yyyy") Date departureDate, 
	                          ModelMap modelMap) {
	    //LOGGER.info("Inside findFlights() From: " + from + " TO: " + to + " Departure Date: " + departureDate);
	    List<Flight> flights = flightRepository.findFlights(from, to, departureDate);
	    
	    //List<Flight> flights = flightRepository.findAll();
	    modelMap.addAttribute("flights", flights);

	    //LOGGER.info("Flight Found are: " + flights);
	    return "displayFlights";
	}

}
