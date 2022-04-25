package com.flightapp.response;

import java.time.LocalDateTime;

public class BookingResponse {
	
	private String PNR;
	public BookingResponse(String message, LocalDateTime timestamp) {
		super();
		this.message = message;
		this.timestamp = timestamp;
	}
	private String message;
	public BookingResponse(String pNR, String message, LocalDateTime localDateTime) {
		
		PNR = pNR;
		this.message = message;
		this.timestamp = localDateTime;
	}
	private LocalDateTime timestamp;
	public String getPNR() {
		return PNR;
	}
	public void setPNR(String pNR) {
		PNR = pNR;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	

}
