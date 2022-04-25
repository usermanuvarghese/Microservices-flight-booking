package com.flightapp.bookingservice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.flightapp.bookingexception.BookingException;
import com.flightapp.bookingrepo.BookingRepo;
import com.flightapp.error.ErrorResponse;
import com.flightapp.model.Booking;
import com.flightapp.model.Passenger;
import com.flightapp.request.BookingRequest;
import com.flightapp.response.BookingResponse;
import com.flightapp.response.FlightResponse;

@Service
public class BookingService {

	@Autowired
	private RestTemplate template;

	@Autowired
	private BookingRepo bookingrepo;

	@Autowired
	PassengerService passengerService;

	public BookingResponse bookFlight(BookingRequest request) throws BookingException {
		Booking b = new Booking();
		try {
			FlightResponse flight = getFlightResponse(request.getFlightID());

			if (null != flight) {
				b.setDateOfBooking(request.getDateOfBooking());
				b.setEmailID(request.getEmailID());
				b.setMobileNumber(request.getMobileNumber());
				b.setFlightID(request.getFlightID());
				b.getPassenger().addAll(request.getPassengers());
				b.setPNR(generatePNR().toUpperCase());
				passengerService.addPassenger(request.getPassengers());
				bookingrepo.save(b);
			} else {
				throw new Exception();
			}
		} catch (Exception e) {
			throw new BookingException("Invalid Booking Details");
		}
		return new BookingResponse(b.getPNR(), "Successfully Booked Ticket", LocalDateTime.now());

	}

	private FlightResponse getFlightResponse(String flightid) {

		String url = "lb://Flight/flightapp/getFlight/" + flightid;
		HttpMethod method = HttpMethod.GET;
		ParameterizedTypeReference type = new ParameterizedTypeReference<FlightResponse>() {
		};
		ResponseEntity<FlightResponse> response = template.exchange(url, method, null, type);

		return response.getBody();

	}

	private String generatePNR() {
		int n=6;
		int lowerLimit = 97;
		int upperLimit = 122;
		Random random = new Random();

		StringBuffer r = new StringBuffer(n);

		for (int i = 0; i < 6; i++) {

			int nextRandomChar = lowerLimit + (int) (random.nextFloat() * (upperLimit - lowerLimit + 1));

			r.append((char) nextRandomChar);
		}
		return r.toString();
	}

	public Booking getBookingDetails(String pnr) throws BookingException {

		Optional<Booking> booking = bookingrepo.findById(pnr);
		if (booking.isPresent()) {
			return booking.get();
		} else {
			System.out.println("Database does not have any Booking with PNR: " + pnr);
			throw new BookingException("No Booking Found");
		}

	}

	public List<Booking> getAllBookingDetails() throws BookingException {
		List<Booking> bookings = bookingrepo.findAll();
		if (null != bookings && !bookings.isEmpty()) {
			return bookings;
		} else {
			throw new BookingException("No Bookings Found");
		}
	}

	public List<Booking> getBookingHistoryByEmail(String emailId) throws BookingException {

		List<Booking> bookings = bookingrepo.findByemailID(emailId);
		if (null != bookings && !bookings.isEmpty()) {
			return bookings;
		} else {
			throw new BookingException("No Bookings Found");
		}
	}

	public void cancelBooking(String pnr) throws BookingException {
		try {
			Booking cancelPnr = bookingrepo.findBypnr(pnr);
			if (null != cancelPnr) {
				bookingrepo.delete(cancelPnr);
				passengerService.deletepassenger(cancelPnr.getPassenger());
			} else {
				throw new BookingException("No Bookings Found");
			}

		} catch (Exception e) {
			throw new BookingException("No Bookings Found");
		}
	}
}
