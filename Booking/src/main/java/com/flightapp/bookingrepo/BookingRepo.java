package com.flightapp.bookingrepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flightapp.model.Booking;

public interface BookingRepo extends JpaRepository<Booking, String> {

	List<Booking> findByemailID(String emailID);

	Booking findBypnr(String pnr);

}
