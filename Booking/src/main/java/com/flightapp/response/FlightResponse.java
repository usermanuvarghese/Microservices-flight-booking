package com.flightapp.response;

import java.sql.Date;
import java.sql.Time;

public class FlightResponse {
	
	private String flightId;
	private String airlineName;
	private String orgin;
	private String destination;
	private Date dateOfDeparture;
	private Date dateOfArrival;
	private Time timeOfDeparture;
	private Time timeofArrival;
	private int numberOfEconomySeats;
	private int numberOfBusinessSeats;

	public int getNumberOfEconomySeats() {
		return numberOfEconomySeats;
	}

	public void setNumberOfEconomySeats(int numberOfEconomySeats) {
		this.numberOfEconomySeats = numberOfEconomySeats;
	}

	public int getNumberOfBusinessSeats() {
		return numberOfBusinessSeats;
	}

	public void setNumberOfBusinessSeats(int numberOfBusinessSeats) {
		this.numberOfBusinessSeats = numberOfBusinessSeats;
	}

	public Time getTimeOfDeparture() {
		return timeOfDeparture;
	}

	public void setTimeOfDeparture(Time timeOfDeparture) {
		this.timeOfDeparture = timeOfDeparture;
	}

	public Time getTimeofArrival() {
		return timeofArrival;
	}

	public void setTimeofArrival(Time timeofArrival) {
		this.timeofArrival = timeofArrival;
	}

	private String flightStatus;

	public String getFlightStatus() {
		return flightStatus;
	}

	public void setFlightStatus(String flightStatus) {
		this.flightStatus = flightStatus;
	}

	public String getFlightId() {
		return flightId;
	}

	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}

	public String getAirlineName() {
		return airlineName;
	}

	public void setAirlineName(String airlineName) {
		this.airlineName = airlineName;
	}

	public String getOrgin() {
		return orgin;
	}

	public void setOrgin(String orgin) {
		this.orgin = orgin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Date getDateOfDeparture() {
		return dateOfDeparture;
	}

	public void setDateOfDeparture(Date dateOfDeparture) {
		this.dateOfDeparture = dateOfDeparture;
	}

	public Date getDateOfArrival() {
		return dateOfArrival;
	}

	public void setDateOfArrival(Date dateOfArrival) {
		this.dateOfArrival = dateOfArrival;
	}

}
