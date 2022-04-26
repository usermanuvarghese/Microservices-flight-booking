package com.flightapp.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.function.Executable;
import com.flightapp.exception.FlightNotFoundException;
import com.flightapp.model.Flight;

@SpringBootTest
class FlightControllerTest {

	@Autowired
	private FlightController flightController;

	@Test
	void searchFlightTest() throws FlightNotFoundException {

		String from = "Singapore";
		String to = "Mumbai";
		String depDate = "2022-04-28";
		assertEquals("AIRINDIA", flightController.searchFlight(from, to, depDate).get(0).getAirlineName());

	}
	
	/*
	 * @Test public void searchFlightTestInvalid() {
	 * 
	 * Assertions.assertThrows(FlightNotFoundException.class,()->{
	 * flightController.searchFlight("a", "Singapore", "2022-04-28") });
	 * 
	 * }
	 */
	 

}
