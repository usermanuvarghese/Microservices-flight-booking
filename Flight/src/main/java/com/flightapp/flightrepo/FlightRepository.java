package com.flightapp.flightrepo;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.flightapp.model.Flight;

public interface FlightRepository extends JpaRepository<Flight, Integer> {

	@Query(value = "SELECT * FROM flight WHERE orgin = ?1 AND destination= ?2 AND date_of_departure=?3 AND flight_status='ENABLED' AND (number_of_business_seats>0 OR number_of_economy_seats>0) ", nativeQuery = true)
	List<Flight> searchFlight(String orgin, String destination, Date date);

	Flight findByflightId(String flightid);

}
