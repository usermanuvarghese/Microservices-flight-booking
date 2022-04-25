package com.flightapp.userservice;

import java.util.Arrays;
import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.flightapp.model.Flight;

@Service
public class KafkaHelper {

	@Autowired
	KafkaTemplate<String, Flight> kafkaTemplate;

	private static final String TOPIC = "FlightInfo";

	public void sendFlightDetails(Flight flight) {
		kafkaTemplate.send(TOPIC, flight);
		
	}

}
