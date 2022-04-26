package com.flightapp.login.controller;

import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.flightapp.login.Response.BookingResponse;
import com.flightapp.login.Response.FlightResponse;
import com.flightapp.login.Response.LoginResponse;
import com.flightapp.login.entity.UserEntity;
import com.flightapp.login.service.UserService;
import com.flightapp.model.Flight;

@RestController
@RequestMapping("/login")
public class UserController {

	@Autowired
	private KafkaTemplate<String, Flight> kafkaTemplate;

	@Autowired
	private UserService userService;

	@PostMapping("/registerUser")
	public LoginResponse registerUser(@RequestBody UserEntity user) {
		userService.registerUser(user);
		return new LoginResponse("Succefully Registered");
	}

	@GetMapping("/home")
	public LoginResponse home() {
		return new LoginResponse("Success");
	}

	@PostMapping("/manageflightStatus")
	public String blockFlight(@RequestBody Flight flight) {
		kafkaTemplate.send("FlightInfo", flight);
		return "Added Flight Details to Kafka";
	}

}
