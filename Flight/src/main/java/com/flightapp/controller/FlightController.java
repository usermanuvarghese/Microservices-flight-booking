package com.flightapp.controller;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.annotation.Generated;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Consumer;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flightapp.exception.FlightNotFoundException;
import com.flightapp.model.Flight;
import com.flightapp.model.FlightResponse;
import com.flightapp.userservice.FlightService;

@RestController
@RequestMapping("/flightapp")
public class FlightController {

	@Autowired
	private FlightService service;

	@GetMapping("/search")
	public List<Flight> searchFlight(@RequestParam(name = "from", required = false) String orgin,
			@RequestParam(name = "to", required = false) String destination,
			@RequestParam(name = "date") String dateOfDeparture) throws FlightNotFoundException {

		return service.getflights(orgin, destination, dateOfDeparture);
	}

	@GetMapping("/getall")
	public List<Flight> getAllFlights() throws FlightNotFoundException {
		return service.getAllFlights();
	}

	@GetMapping("/getFlight/{flightid}")
	public Flight getFlightDetails(@PathVariable String flightid) throws FlightNotFoundException {
		return service.getflightById(flightid);
	}

	@PostMapping("/registerAirline")
	public ResponseEntity<?> registerAirline(@RequestBody Flight flight) {
		return service.registerAirline(flight);
	}

	@KafkaListener(topics = "FlightInfo", groupId = "group_id", containerFactory = "userKafkaListenerContainerFactory")
	public void getflights(Flight flight) throws FlightNotFoundException {
		service.blockFlight(flight);
	}

}
