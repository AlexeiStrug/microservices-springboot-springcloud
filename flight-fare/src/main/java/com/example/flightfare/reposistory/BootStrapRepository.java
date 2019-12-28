package com.example.flightfare.reposistory;

import com.example.flightfare.model.FlightFare;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class BootStrapRepository implements CommandLineRunner {

    private FlightFareRepository flightFareRepository;

    @Autowired
    public BootStrapRepository(FlightFareRepository flightFareRepository) {
        this.flightFareRepository = flightFareRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        FlightFare flightFare1 = new FlightFare(1L, "UL-191", BigDecimal.valueOf(1000));
        FlightFare flightFare2 = new FlightFare(2L, "UL-192", BigDecimal.valueOf(2000));
        FlightFare flightFare3 = new FlightFare(3L, "UL-193", BigDecimal.valueOf(3000));
        FlightFare flightFare4 = new FlightFare(4L, "UL-194", BigDecimal.valueOf(4000));

        flightFareRepository.save(flightFare1);
        flightFareRepository.save(flightFare2);
        flightFareRepository.save(flightFare3);
        flightFareRepository.save(flightFare4);
    }
}
