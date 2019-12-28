package com.example.flightSchedule.services.iml;

import com.example.flightSchedule.models.Flight;
import com.example.flightSchedule.repository.FlightScheduleRepository;
import com.example.flightSchedule.services.FlightScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("flightScheduleService")
public class FlightScheduleServiceImpl implements FlightScheduleService {

    @Value("${airline.disabled}")
    private String airlineDisable;
    @Autowired
    private FlightScheduleRepository flightScheduleRepository;

    @Override
    public List<Flight> getFlights(String from, String to) {
        Flight filterFlight = new Flight();
        filterFlight.setFlightFrom(from);
        filterFlight.setFlightTo(to);

        Example<Flight> flightFilter = Example.of(filterFlight);
        List<Flight> flights = flightScheduleRepository.findAll(flightFilter);

        if (Optional.of(flights).isPresent()) {
            flights = flights.stream().filter(flight -> !airlineDisable.equals(flight.getAirline())).collect(Collectors.toList());
        }
        return flights;
    }
}
