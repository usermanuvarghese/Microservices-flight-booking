package com.flightapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flightapp.bookingexception.BookingException;
import com.flightapp.bookingservice.BookingService;
import com.flightapp.model.Booking;
import com.flightapp.request.BookingRequest;
import com.flightapp.response.BookingResponse;

@RestController
@RequestMapping("/flight")
public class BookingController {

	@Autowired
	private BookingService bookingService;

	@PostMapping("/bookflight")
	public BookingResponse bookFlight(@RequestBody BookingRequest request) throws BookingException {
		return bookingService.bookFlight(request);
		
	}

	@GetMapping("/manageBooking/{pnr}")
	public Booking manageBooking(@PathVariable(name = "pnr") String pnr) throws BookingException {
		return bookingService.getBookingDetails(pnr);
	}
	 
	@GetMapping("/bookingHistory/{emailId}")
	public List<Booking> bookingHistory(@PathVariable(name = "emailId") String emailId) throws BookingException {
		return bookingService.getBookingHistoryByEmail(emailId);
		
	}
	
	@GetMapping("/getallBookings")
	public List<Booking> getAllBookings() throws BookingException {
		return bookingService.getAllBookingDetails();
	}
	
	@DeleteMapping("/cancelBooking/{pnr}")
	public String cancelBooking(@PathVariable(name = "pnr") String pnr) throws BookingException {
		bookingService.cancelBooking(pnr);
		return "success";
		
	}
	

}
