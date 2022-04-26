package com.flightapp.model;

import java.time.LocalDateTime;

public class FlightResponse {

	private String message;
	private LocalDateTime date;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public FlightResponse(String message, LocalDateTime date) {
		super();
		this.message = message;
		this.date = date;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

}
