package com.example.flightSchedule.repository;

import com.example.flightSchedule.models.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapRepository implements CommandLineRunner {

    private final FlightScheduleRepository flightScheduleRepository;

    @Autowired
    public BootStrapRepository(FlightScheduleRepository flightScheduleRepository) {
        this.flightScheduleRepository = flightScheduleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Flight flight1 = new Flight(1L, "UL_191", "DEL", "TYO", "Srilankan Airlines", "05:10", "7:35");
        Flight flight2 = new Flight(2L, "UL_192", "DEL", "TYO", "Chine Southern Airlines", "05:15", "9:35");
        Flight flight3 = new Flight(3L, "UL_193", "DEL", "TYO", "Chine Southern Airlines", "12:30", "14:35");
        Flight flight4 = new Flight(4L, "UL_194", "DEL", "TYO", "Srilankan Airlines", "15:10", "18:40");

        flightScheduleRepository.save(flight1);
        flightScheduleRepository.save(flight2);
        flightScheduleRepository.save(flight3);
        flightScheduleRepository.save(flight4);
    }
}
