package com.example.flightSchedule.services.iml;

import com.example.flightSchedule.models.Flight;
import com.example.flightSchedule.repository.FlightScheduleRepository;
import com.example.flightSchedule.services.FlightScheduleService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service("flightScheduleService")
public class FlightScheduleServiceImpl implements FlightScheduleService {

    @Value("${airline.disabled}")
    private String airlineDisable;
    @Autowired
    private FlightScheduleRepository flightScheduleRepository;

    @HystrixCommand(commandKey = "getFlightsKey", fallbackMethod = "buildFallbackFlights", threadPoolKey = "getFlightThreadPool", threadPoolProperties = {
            @HystrixProperty(name = "coreSize", value = "1"),
            @HystrixProperty(name = "maxQueueSize", value = "2")
    })
//    @HystrixCommand
    @Override
    public List<Flight> getFlights(String from, String to) {
        System.out.println(Thread.currentThread().getName());
        Flight filterFlight = new Flight();
        filterFlight.setFlightFrom(from);
        filterFlight.setFlightTo(to);

        Example<Flight> flightFilter = Example.of(filterFlight);
        sleepRandomly();
        List<Flight> flights = flightScheduleRepository.findAll(flightFilter);

        if (Optional.of(flights).isPresent()) {
            flights = flights.stream().filter(flight -> !airlineDisable.equals(flight.getAirline())).collect(Collectors.toList());
        }
        return flights;
    }

    private void sleepRandomly() {
        Random random = new Random();
        int rnd = random.nextInt(3) + 1;
        if (rnd == 3) {
            System.out.println("It is a chance for demostraing Hystrix action.");
            try {
                System.out.println("Start sleeping... " + System.currentTimeMillis());
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.out.println("Hystrix thread interupted... " + System.currentTimeMillis());
                e.printStackTrace();
            }
        }
    }

    private List<Flight> buildFallbackFlights(String from, String to) {
        Flight flight = new Flight();
        flight.setId(0L);
        flight.setFlightFrom(from);
        flight.setFlightTo(to);
        flight.setFlightCode("Sorry, no flight available for this time.");
        flight.setAirline("N/A");
        flight.setDepartureTime("N/A");
        flight.setArrivalTime("N/A");
        List<Flight> flights = new ArrayList<>();
        flights.add(flight);
        return flights;
    }
}
