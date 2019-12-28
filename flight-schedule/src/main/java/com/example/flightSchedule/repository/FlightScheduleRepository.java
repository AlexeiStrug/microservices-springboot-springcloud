package com.example.flightSchedule.repository;

import com.example.flightSchedule.models.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightScheduleRepository extends JpaRepository<Flight, Long> {
}
