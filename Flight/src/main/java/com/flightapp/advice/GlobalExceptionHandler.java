package com.flightapp.advice;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.flightapp.error.ErrorResponse;
import com.flightapp.exception.FlightNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(FlightNotFoundException.class)
	public ResponseEntity<ErrorResponse> handle(FlightNotFoundException e) {
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(e.getMessage(), LocalDateTime.now()),

				HttpStatus.OK);
	}

}
