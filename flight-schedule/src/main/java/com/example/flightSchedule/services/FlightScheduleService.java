package com.example.flightSchedule.services;

import com.example.flightSchedule.models.Flight;
import org.springframework.stereotype.Service;

import java.util.List;

public interface FlightScheduleService {

    List<Flight> getFlights(String from, String to);
}
