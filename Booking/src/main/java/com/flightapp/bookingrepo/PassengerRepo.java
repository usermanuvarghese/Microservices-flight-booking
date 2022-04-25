package com.flightapp.bookingrepo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flightapp.model.Passenger;

public interface PassengerRepo extends JpaRepository<Passenger,String> {


    

}
