package com.flightapp.bookingadvice;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.flightapp.bookingexception.BookingException;
import com.flightapp.error.ErrorResponse;


@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(BookingException.class)
	public ResponseEntity<ErrorResponse> handle(BookingException e) {
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(e.getMessage(), LocalDateTime.now()),

				HttpStatus.OK);
	}

}
