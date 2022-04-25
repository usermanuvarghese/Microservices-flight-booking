package com.flightapp.userservice;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.flightapp.error.ErrorResponse;
import com.flightapp.exception.FlightNotFoundException;
import com.flightapp.flightrepo.FlightRepository;
import com.flightapp.model.Flight;
import com.flightapp.model.FlightResponse;

@Service
public class FlightService {

	@Autowired
	private FlightRepository flightRepo;

	@Autowired
	private KafkaHelper kafkaHelper;

	public List<Flight> getflights(String orgin, String destination, String dateOfDeparture)
			throws FlightNotFoundException {
		List<Flight> flights = new ArrayList<Flight>();
		try {
			// SimpleDateFormat formatter=new SimpleDateFormat("yyyy-mm-dd");
			Date date = Date.valueOf(dateOfDeparture);
			flights = flightRepo.searchFlight(orgin, destination, date);

			if (null == flights || flights.isEmpty())
				throw new Exception("No Flights found");

		} catch (Exception e) {
			throw new FlightNotFoundException("No Flights found");
		}
		return flights;
	}

	public List<Flight> getAllFlights() throws FlightNotFoundException {
		try {
			List<Flight> flights = flightRepo.findAll();
			// List<Flight> flights = kafkaHelper.getflights();
			if (flights != null)
				return flights;
			else
				throw new Exception();
		} catch (Exception e) {
			throw new FlightNotFoundException("Flight not found");
		}
	}

	public ResponseEntity<?> registerAirline(Flight flight) {
		try {
			flightRepo.save(flight);

		} catch (Exception e) {
			return new ResponseEntity<ErrorResponse>(new ErrorResponse("Invalid Details", LocalDateTime.now()),
					HttpStatus.BAD_REQUEST);

		}
		return new ResponseEntity<FlightResponse>(
				new FlightResponse("Successfully entered data to DB", LocalDateTime.now()), HttpStatus.OK);

	}

	public Flight getflightById(String flightid) throws FlightNotFoundException {
		try {
			Flight flight = flightRepo.findByflightId(flightid);
			if (flight == null) {
				throw new Exception();
			} else
				return flight;
		} catch (Exception e) {
			throw new FlightNotFoundException("Flight not found");
		}
	}

	public void blockFlight(String flightId) throws FlightNotFoundException {
		try {
			Flight flight = flightRepo.findByflightId(flightId);
			if (flight == null) {
				throw new Exception();
			} else
			{
				flight.setFlightStatus("BLOCKED");
				flightRepo.save(flight);
			}
		} catch (Exception e) {
			throw new FlightNotFoundException("Flight not found");
		}
		
	}

}
