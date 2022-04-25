package com.flightapp.bookingservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightapp.bookingrepo.PassengerRepo;
import com.flightapp.model.Booking;
import com.flightapp.model.Passenger;
import com.flightapp.request.BookingRequest;

@Service
public class PassengerService {

	@Autowired
	private PassengerRepo passengerRepo;

	public void addPassenger(List<Passenger> passengers) {

		// Passenger passenger = new Passenger;
		// passenger.setAddress(null);
		passengerRepo.saveAll(passengers);

	}

	public void deletepassenger(List<Passenger> passengers) {
		passengerRepo.deleteAll(passengers);
		
	}
}
